(defproject simple-json "0.1.0-SNAPSHOT"
  :description "This a project that demonstrates how to maniplate data in a file /root/data/big-data.json"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.clojure/data.json "2.4.0"]
  :main ^:skip-aot simple-json.core
  :target-path "target/%s"
  :jvm-opts ["-Xmx1g"]
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
