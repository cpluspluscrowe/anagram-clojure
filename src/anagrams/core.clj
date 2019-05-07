(ns anagrams.core
  (:require [clojure.string :as string])
  (:gen-class))

(defn remove-letter-from-word [word letter]
  (string/replace-first word letter ""))

(defn word-to-letters [word]
  (map str (seq word)))

(defn remove-empty-items-from-list [list]
  (remove #(= % "") list))

(defn get-all-words-one-letter-fewer [word]
          (remove-empty-items-from-list
          (map (partial remove-letter-from-word word)  (word-to-letters word))))

(defn get-letter-combinations [word]
  (let [words-one-letter-fewer (get-all-words-one-letter-fewer word)]
    (cond (= 0 (count words-one-letter-fewer)) word
          :else (conj words-one-letter-fewer (list (map #(get-letter-combinations %) words-one-letter-fewer))))))

(defn is-odd? [count]
  (not (even? count)))

(defn count-anagrams [word]
  (let [letter-frequencies (frequencies word)
        odds (filter is-odd? (vals letter-frequencies))
        evens (filter even? (vals letter-frequencies))
        odd-count (count odds)
        even-count (count evens)
        has-anagrams (= odd-count 1)]
    (cond has-anagrams (+ odd-count even-count)
          :else 0
    )))

(defn get-distinct-letter-combinations [word]
  (set (flatten (get-letter-combinations word))))

(defn hr [input]
  (println input))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [input "abbad"]
    (println (count-anagrams input))))

