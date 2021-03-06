(ns playphraseme.api.routes.phrases
  (:require [playphraseme.api.middleware.cors :refer [cors-mw]]
            [playphraseme.api.route-functions.search.phrases-search :refer :all]
            [compojure.api.sweet :refer :all]
            [schema.core :as s]))

(def phrases-routes
  "Specify routes for Pharses Searching"
  (context "/api/v1/phrases" []
    :tags ["Phrases"]

    (GET "/search"   request
      :tags          ["Phrases"]
      :return        s/Any
      :middleware    [cors-mw]
      :query-params  [q :- s/Str {skip :- s/Num 0} {limit :- s/Num 10}]
      :summary       "Return phrases search result"
      (search-response q skip limit))

    (GET "/count"    request
      :tags          ["Phrases"]
      :return        s/Num
      :middleware    [cors-mw]
      :query-params  [q :- s/Str]
      :summary       "Return phrases count"
      (count-response q))

    (GET "/all-phrases-count"   request
      :tags          ["Phrases"]
      :return        s/Num
      :middleware    [cors-mw]
      :summary       "Return all phrases count"
      (all-phrases-count-response))

    (GET "/all-movies-count"   request
      :tags          ["Phrases"]
      :return        s/Num
      :middleware    [cors-mw]
      :summary       "Return all movies count"
      (all-movies-count-response))

    (GET "/video-url"   request
         :tags          ["Phrases"]
         :return        s/Str
         :middleware    [cors-mw]
         :query-params  [id :- s/Str]
         :summary       "Phrase video url"
         (video-url-response id))

    (GET "/video"   request
      :tags          ["Phrases"]
      :return        s/Str
      :middleware    [cors-mw]
      :query-params  [id :- s/Str]
      :summary       "Phrase stream video"
      (video-response id))

    ;; (GET "/video-stream"   request
    ;;      :tags          ["Phrases"]
    ;;      :return        s/Str
    ;;      :middleware    [cors-mw]
    ;;      :query-params  [id :- s/Str]
    ;;      :summary       "Phrase stream video"
    ;;      (video-stream-response id))

    (GET "/video-download"   request
      :tags          ["Phrases"]
      :middleware    [cors-mw]
      :query-params  [id :- s/Str]
      :summary       "Download phrase video file"
      (video-download-response id))

    (GET "/phrase/:id"         [id :as request]
         :tags          ["Phrases"]
         :return        s/Any
         :middleware    [cors-mw]
         :summary       "Return phrase by id"
         (phrase-response id))))

