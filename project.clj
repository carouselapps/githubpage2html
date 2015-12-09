(defproject com.carouselapps/githubpage2html "0.1.0-SNAPSHOT"
  :description "Converts github pages to HTML ready to use for example in WordPress posts"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"] [enlive "1.1.6"]]
  :main ^:skip-aot githubpage2html.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
