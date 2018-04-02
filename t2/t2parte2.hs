--1
isBin :: String -> Bool
isBin "" = True
isBin x = 
  if (head x == '0' || head x == '1') then isBin (tail x)
  else False
  
--2
isBin' :: String -> Bool
isBin' x = not (any (>'1') x)

--3
bin2dec :: [Int] -> Int
bin2dec [] = undefined
bin2dec bits = auxBin2Dec bits ((length bits)-1)

auxBin2Dec :: [Int] -> Int -> Int
auxBin2Dec (x:xs) y = x * (2^y) + auxBin2Dec xs (y-1)

--4
bin2dec' :: [Int] -> Int
bin2dec' n = sum (zipWith (*) (map (\x -> 2^x)  ([(length n)-1,(length n)-2..0])) n)

--5
dec2bin :: Int -> [Int]
dec2bin 0 = []
dec2bin x = x `rem` 2 : dec2bin (x `quot` 2)
