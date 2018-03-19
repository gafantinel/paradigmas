import Data.Char

--1
isVowel :: Char -> Bool
isVowel x = (x=='a' || x=='e' || x=='i' || x=='o' || x=='u')

--2
addComma :: [String] -> [String]
addComma x = map (\x -> x ++ ",") x

--3
htmlListItems :: [String] -> [String]
htmlListItems x = map (\n -> "<LI>"++ n ++"</LI>") x

htmlListItems1 :: String -> String
htmlListItems1 x = ("<LI>" ++ x ++ "</LI>")
htmlListItems2 :: [String] -> [String]
htmlListItems2 x = map htmlListItems1 x

--4
semVogais :: String -> String
semVogais x = (filter (\x -> (x/='a' && x/='e' && x/='i' && x/='o' && x/='u')) x)

semVogais1 :: Char -> Bool
semVogais1 x = (x/='a' && x/='e' && x/='i' && x/='o' && x/='u')
semVogais2 :: String -> String
semVogais2 x = filter semVogais1 x

--5
codifica :: String -> String
codifica x = map (\n -> if n/= ' ' then '-' else n) x

codifica1 :: Char -> Char
codifica1 x = if x/=' ' then '-' else x
codifica2 :: String -> String
codifica2 x = map codifica1 x

--6
firstName :: String -> String
firstName x = takeWhile (/=' ') x

firstName1 :: Char -> Bool
firstName1 x = x/=' '
firstName2 :: String-> String
firstName2 x = takeWhile firstName1 x

--7
isInt :: String -> Bool
isInt x = all (\n -> n>='0' && n<='9') x

--8
lastName :: String -> String
lastName x = reverse (takeWhile (/=' ') (reverse x))

--9
userName :: String -> String
userName x = map toLower ((head x:[])++(lastName x))

--10
cadaLetra :: Char -> Char
cadaLetra x
  | (toLower x) == 'a' = '4'
  | (toLower x) == 'e' = '3'
  | (toLower x) == 'i' = '2'
  | (toLower x) == 'o' = '1'
  | (toLower x) == 'u' = '0'
  | otherwise = x

encodeName :: String -> String
encodeName x = map cadaLetra x

--11
cadaLetra2 :: Char -> [Char]
cadaLetra2 x
  | (toLower x) == 'a' = "4"
  | (toLower x) == 'e' = "3"
  | (toLower x) == 'i' = "1"
  | (toLower x) == 'o' = "0"
  | (toLower x) == 'u' = "00"
  | otherwise = [x]

betterEncodeName :: String -> String
betterEncodeName x = concatMap cadaLetra2 x

--12
lista10 :: String -> String
lista10 x =
    if (length x)>10 then take 10 x
    else if (length x)<10 then lista10 (x ++ ".")
    else x

lista :: [String] -> [String]
lista x = map lista10 x







