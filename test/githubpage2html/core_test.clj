(ns githubpage2html.core-test
  (:require [clojure.test :refer :all]
            [githubpage2html.core :refer :all]))

(deftest core
  (testing "get-lang."
    (is (= "clojure" (get-lang "highlight highlight-source-clojure")))
    (is (= "ruby" (get-lang "highlight highlight-source-ruby")))
    (is (= nil (get-lang "highlight highlight-source-")))
    (is (= nil (get-lang "random text")))
    (is (= "clojure" (get-lang "highlight highlight-source-clojure other-class"))))
  (testing "crayonify"
   (is (= {:tag :pre} (crayonify {:tag :div :content [{:tag :pre}]})))
   (is (= {:tag :pre,
           :attrs {:class "lang:clojure decode:true"},
           :content ["[free-form.re-frame/form @values @errors ("
                     "fn"
                     " [ks new-value] ["
                     ":update"
                     " "
                     ":user"
                     " ks ne-value])\n[...]]"]} (crayonify {:tag :div,
                                                            :attrs {:class "highlight highlight-source-clojure"},
                                                            :content [{:tag :pre,
                                                                       :attrs nil,
                                                                       :content ["[free-form.re-frame/form @values @errors ("
                                                                                 "fn"
                                                                                 " [ks new-value] ["
                                                                                 ":update"
                                                                                 " "
                                                                                 ":user"
                                                                                 " ks ne-value])\n[...]]"]}]})))))