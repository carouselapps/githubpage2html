(ns githubpage2html.core
    (:require [net.cgrand.enlive-html :as html])
    (:use [clojure.string :only (join)])
    (:gen-class))

(defn fetch-url
      [url] (html/html-resource (java.net.URL. url)))

(defn newline-to-space
      [s] (clojure.string/replace s #"\n" " "))

(defn get-content
      [url] (html/at                                        ; content without h1
              (html/select (fetch-url url) [:article :> :*])
              [:h1] nil
              [:p html/text-node] (fn [node] (newline-to-space node))))

(defn gen-html
      [html-content] (join "" (html/emit* html-content)))

(defn -main
      "Get the page content, extract the important part and send it to the output."
      [& args]
      (println (gen-html (get-content (first args)))))