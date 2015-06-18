(defproject om-material-ui "0.2.0-SNAPSHOT"
  :description "A simple wrapper around react Material UI"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [prismatic/plumbing "0.4.2"]
                 [prismatic/om-tools "0.3.11" :exclusions [org.clojure/clojure]]
                 [org.clojure/clojurescript "0.0-2727" :scope "provided"]
                 [om "0.8.0-rc1"]]
  :source-paths ["target/generated/src/clj" "src"]
  :resource-paths ["target/generated/src/cljs"]
  :jar-exclusions [#"\.cljx|\.swp|\.swo|\.DS_Store"]
  :prep-tasks [["cljx" "once"] "javac" "compile"]
  :profiles {:dev {:plugins [[com.keminglabs/cljx "0.5.0"
                              :exclusions [org.clojure/clojure]]]}}
  :cljx {:builds [{:source-paths ["src"]
                   :output-path "target/generated/src/clj"
                   :rules :clj}
                  {:source-paths ["src"]
                   :output-path "target/generated/src/cljs"
                   :rules :cljs}]})
