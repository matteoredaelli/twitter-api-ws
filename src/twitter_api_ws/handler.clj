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
    (GET* "/users-show" []
      ;;:return Result
      :query-params [screen-name :- String]
      :summary "Retreive info about a user"
      (let [result (users-show :oauth-creds oauth-creds 
                               :params {:screen-name screen-name})]
        (ok (generate-string result))))
    (GET* "/users-show2" []
      ;;:return Result
      :query-params [params :- String]
      :summary "Retreive info about a user"
      (let [result (users-show :oauth-creds oauth-creds 
                               :params (parse-string params))]
        (ok (generate-string result))))
    (GET* "/users-show3" []
      ;;:return Result
      :query-params [params :- String]
      :summary "Retreive info about a user"
      (let [result (load-string  "(users-show :oauth-creds oauth-creds :params (parse-string params true))")]
        (ok (generate-string result))))
    (GET* "/twitter-api" []
      ;;:return Result
      :query-params [function :- String, params :- String]
      :summary "Retreive info about a user"
      (let [parsed-params (parse-string params true)
            result (eval (read-string "(users-show :oauth-creds oauth-creds :params parsed-params)"))]
        (ok (generate-string result))))
    (POST* "/plus" []
      ;;:return Result
      :body-params [x :- Long, {y :- Long 1}]
      :summary "x+y (y default to 1)"
      (ok {:result (+ x y)}))
    (POST* "/plus2" []
      ;;:return Result
      :body-params [x,y]
      :summary "x+y (y default to 1)"
      (ok {:result (+ x y)}))
 ))
