(defproject misr "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [lein-light-nrepl "0.3.2"]
                 [edu.ucar/netcdf "4.3.22"]
                 ]
  :repl-options {:nrepl-middleware [lighttable.nrepl.handler/lighttable-ops]}
                 )
