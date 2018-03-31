
--1
geraTabela :: Int -> [(Int,Int)]
geraTabela 0 = [(0,1)]
geraTabela x = (x,x^2) : geraTabela (x-1)

--2
contido :: Char -> String -> Bool
contido x "" = False
contido x str = 
    if x == (head str) then True
    else contido x (tail str)

--3
translate :: [(Double, Double)] -> [(Double, Double)]
translate [] = []
translate (x:xs) = (fst x + 2, snd x + 2) : translate xs

--4
geraTabela' :: Int -> [(Int,Int)]
geraTabela' x = aux 1 x

aux :: Int -> Int -> [(Int,Int)]
aux x y =
    if x <= y then (x,x^2) : aux (x+1) y
    else []
