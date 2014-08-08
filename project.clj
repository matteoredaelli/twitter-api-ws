;    This program is free software: you can redistribute it and/or modify
;    it under the terms of the GNU General Public License as published by
;    the Free Software Foundation, either version 3 of the License, or
;    (at your option) any later version.
;
;    This program is distributed in the hope that it will be useful,
;    but WITHOUT ANY WARRANTY; without even the implied warranty of
;    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;    GNU General Public License for more details.
;
;    You should have received a copy of the GNU General Public License
;    along with this program.  If not, see <http://www.gnu.org/licenses/>.
;
(defproject twitter-api-ws "0.1.0-SNAPSHOT"
  :description "Twitter-api-ws: a easy web interface to twitter-api"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [metosin/compojure-api "0.13.1"]
                 [metosin/ring-http-response "0.4.0"]
                 [metosin/ring-swagger-ui "2.0.16-2"]
                 ;; my
                 [twitter-api "0.7.5"]
                 ;; for JSON
                 ;;[org.clojure/data.json "0.2.4"]
                 [cheshire "5.3.1"]
                 ;; configuration file
                 [environ "0.5.0"]

]
  :ring {:handler twitter-api-ws.handler/app}
  :uberjar-name "server.jar"
  :profiles {:uberjar {:resource-paths ["swagger-ui"]}
             :dev {:dependencies [[javax.servlet/servlet-api "2.5"]]
                   :plugins [[lein-ring "0.8.11"]
                             [lein-environ "0.5.0"]]
                   }})
