soma :: [Int] -> Int
soma x = sum (zipWith (*) (cycle [1,3]) x)
