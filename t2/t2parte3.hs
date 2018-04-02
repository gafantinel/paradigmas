soma :: [Int] -> Int
soma x = sum (zipWith (*) (cycle [1,3]) x)

decimal :: Int -> Int
decimal x = head (filter (\n -> n >= x) [0,10..])
