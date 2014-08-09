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
(ns twitter-api-ws.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            ;; my
            [cheshire.core :refer :all])
  (:use
   [twitter.oauth]
   [twitter.callbacks]
   [twitter.callbacks.handlers]
   [twitter.api.restful]
   [twitter.utils]
   [twitter.request]
   [twitter.api.restful]
   [twitter.api.search]
   [environ.core]
   )
  (:import
   (twitter.callbacks.protocols SyncSingleCallback)
  )
)

(def oauth-creds (make-oauth-creds
                  (env :twitter-app-consumer-key)
                  (env :twitter-app-consumer-secret)
                  (env :twitter-user-access-token)
                  (env :twitter-user-access-token-secret)
                  ))

(s/defschema Result {:result Long})

(defapi app
  (swagger-ui)
  (swagger-docs
   :title "Twitter-api-rest Api"
   :description "this is Twitter-api-rest Api.")
  (swaggered "twitter"
             :description "playing with parameters"


             (GET* "/twitter-api" []
                   ;;:return Result
                   :query-params [function :- String, params :- String]
                   :summary "Retreive info about a user"
                   (let [rest-function (str "twitter.api.restful/" function)
                         result (eval ((load-string rest-function) 
                                       :oauth-creds oauth-creds 
                                       :params (parse-string params)))]
                     (ok (generate-string result))))

             )
  )
    
