(defproject pingergen "0.1.0-SNAPSHOT"
  :description "A demonstration API project that returns information about the environment in which it runs"
  :url "https://github.com/reselbob/clojureassets/pingergen"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 ; Compojure - A basic routing library
                 [compojure "1.6.1"]
                 ; Our Http library for client/server
                 [http-kit "2.3.0"]
                 ; Ring defaults - for query params etc
                 [ring/ring-defaults "0.3.2"]
                 ; Clojure data.JSON library
                 [org.clojure/data.json "0.2.6"]
                 [com.clojure-goes-fast/clj-memory-meter "0.1.3"]]
  :jvm-opts ["-Djdk.attach.allowAttachSelf"]
  :main ^:skip-aot pingergen.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})