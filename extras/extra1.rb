#Extra: T1 de Haskell feito em Ruby

#1-Crie uma função isVowel :: Char -> Bool que verifique se um caracter é uma vogal ou não.
def isVowel(x)
  if x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u' 
    return true
  else
    return false
  end
end

#2-Escreva uma função addComma, que adicione uma vírgula no final de cada string contida numa lista.
def addComma(*x)
  return x.map {|x| x++","}
end

#3-Crie uma função htmlListItems :: [String] -> [String], que receba uma lista de strings e retorne outra lista contendo as strings formatadas como itens de lista em HTML. Resolva este exercício COM e SEM funções anônimas (lambda). Exemplo de uso da função:
def htmlListItems(*x)
  return x.map {|x| "<LI>"++x++"</LI>"}
end

#4-Defina uma função que receba uma string e produza outra retirando as vogais, conforme os exemplos abaixo.
def semVogais(x)
  return x.tr('aeiou','')
end

#5-Defina uma função que receba uma string, possivelmente contendo espaços, e que retorne outra string substituindo os demais caracteres por '-', mas mantendo os espaços.
def codifica(x)
  return x.tr('A-z','-')
end

#6-Escreva uma função firstName :: String -> String que, dado o nome completo de uma pessoa, obtenha seu primeiro nome. Suponha que cada parte do nome seja separada por um espaço e que não existam espaços no início ou fim do nome.
def firstName(x)
  return x.split.first
end

#7-Escreva uma função isInt :: String -> Bool que verifique se uma dada string só contém dígitos de 0 a 9.
def isInt(x)
  Integer(x)
  true
  rescue ArgumentError
  false
end

#8-Escreva uma função lastName :: String -> String que, dado o nome completo de uma pessoa, obtenha seu último sobrenome. Suponha que cada parte do nome seja separada por um espaço e que não existam espaços no início ou fim do nome. 
def lastName(x)
  return x.split.last
end

#9-Escreva uma função userName :: String -> String que, dado o nome completo de uma pessoa, crie um nome de usuário (login) da pessoa, formado por: primeira letra do nome seguida do sobrenome, tudo em minúsculas. 
def userName(x)
  return (x[0] ++ x.split.last).downcase
end

#10-Escreva uma função encodeName :: String -> String que substitua vogais em uma string, conforme o esquema a seguir: a = 4, e = 3, i = 2, o = 1, u = 0.
def encodeName(x)
  x = x.gsub('a','4')
  x = x.gsub('e','3')
  x = x.gsub('i','2')
  x = x.gsub('o','1')
  x = x.gsub('u','0')
  return x
end

#11-Escreva uma função betterEncodeName :: String -> String que substitua vogais em uma string, conforme este esquema: a = 4, e = 3, i = 1, o = 0, u = 00.
def betterEncodeName(x)
  x = x.gsub('a','4')
  x = x.gsub('e','3')
  x = x.gsub('i','1')
  x = x.gsub('o','0')
  x = x.gsub('u',"00")
  return x
end

#12-Dada uma lista de strings, produzir outra lista com strings de 10 caracteres, usando o seguinte esquema: strings de entrada com mais de 10 caracteres são truncadas, strings com até 10 caracteres são completadas com '.' até ficarem com 10 caracteres.
def func(*x)
  y = x.map {|x| x++".........."}
  z = y.take(10)
  return z.map {|z| z[0,10]}
end
