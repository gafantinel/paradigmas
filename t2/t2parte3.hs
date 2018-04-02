import Data.Char

soma :: [Int] -> Int
soma x = sum (zipWith (*) (cycle [1,3]) x)

decimal :: Int -> Int
decimal x = head (filter (\n -> n >= x) [0,10..])

isEanOk :: String -> Bool
isEanOk x = 
    if ((decimal (soma (map digitToInt x)) - soma (map digitToInt x)) == last (map digitToInt x)) then True
    else False
