(ns githubpage2html.core
    (:require [net.cgrand.enlive-html :as html])
    (:use [clojure.string :only (join)])
    (:gen-class))

(defn fetch-url
      [url] (html/html-resource (java.net.URL. url)))

(defn newline-to-space
      [s] (clojure.string/replace s #"\n" " "))

(defn get-lang [string]
      (if (not (nil? string))
        (let [lang (last (first (re-seq #"highlight highlight-source-([^\s]*)" string)))]
           (cond
             (empty? lang) nil
             (= string lang) nil
             :else lang))))

(defn crayonify [div-node]
      (let [node (first (html/unwrap div-node))]
           (if-let [lang (get-lang (get-in div-node [:attrs, :class]))]
                   ((html/add-class (str "lang:" lang) "decode:true") node)
                   node)))

(defn get-content
      [url] (html/at
              (html/select (fetch-url url) [:article :> :*])
              [:h1] nil                                     ; strip h1 from content
              [:p html/text-node] (fn [node] (newline-to-space node))
              [:div :pre :span] html/unwrap
              [:div :pre :code] html/unwrap
              [:div.highlight] crayonify))

(defn gen-html
      [html-content] (join "" (html/emit* html-content)))

(defn -main
      "Get the page content, extract the important part and send it to the output."
      [& args]
      (println (gen-html (get-content (first args)))))