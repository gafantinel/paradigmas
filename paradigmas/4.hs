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

--testeasdasdasdasdsad