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
auxBin2Dec x = 