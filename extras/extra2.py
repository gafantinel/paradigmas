#Extra: T1 de Haskell feito em Python

# 1 - Crie uma função isVowel :: Char -> Bool que verifique se um caracter é uma vogal ou não.
def isVowel(x):
  return x in "aeiou"

#2-Escreva uma função addComma, que adicione uma vírgula no final de cada string contida numa lista.
def addComma(x):
  return "".join (list(map(lambda x:x+"," ,x)))
  
#3-Crie uma função htmlListItems :: [String] -> [String], que receba uma lista de strings e retorne outra lista contendo as strings formatadas como itens de lista em HTML. Resolva este exercício COM e SEM funções anônimas (lambda).
def htmlListItems(x):
  return list(map(lambda x: "<LI>"+x+"</LI>" ,x))
  
#4-Defina uma função que receba uma string e produza outra retirando as vogais, conforme os exemplos abaixo.
def semVogais(x):
  return "".join(list(filter(lambda x: x not in "aeiou",x)))
  
#5-Defina uma função que receba uma string, possivelmente contendo espaços, e que retorne outra string substituindo os demais caracteres por '-', mas mantendo os espaços.

def codifica(x):
  return "".join(list(map(lambda x: '-' if x != ' ' else ' ', x)))
  
#6-Escreva uma função firstName :: String -> String que, dado o nome completo de uma pessoa, obtenha seu primeiro nome. Suponha que cada parte do nome seja separada por um espaço e que não existam espaços no início ou fim do nome.
def firstName(x):
  return x.split(' ')[0]
  
#7-Escreva uma função isInt :: String -> Bool que verifique se uma dada string só contém dígitos de 0 a 9.
def isIntAux(x):
  try:
    int(x)
  except ValueError:
    return False
  else:
    return True

def isInt(x):
  return isIntAux(x)

#8-Escreva uma função lastName :: String -> String que, dado o nome completo de uma pessoa, obtenha seu último sobrenome. Suponha que cada parte do nome seja separada por um espaço e que não existam espaços no início ou fim do nome. 
def lastName(x):
  return x.split(' ')[-1]

#9-Escreva uma função userName :: String -> String que, dado o nome completo de uma pessoa, crie um nome de usuário (login) da pessoa, formado por: primeira letra do nome seguida do sobrenome, tudo em minúsculas. 
def userName(x):
  user = x[0] + lastName(x)
  return user.lower()
  
#10-Escreva uma função encodeName :: String -> String que substitua vogais em uma string, conforme o esquema a seguir: a = 4, e = 3, i = 2, o = 1, u = 0.

def encode1aux(x):
  if x == 'a':
    return '4'
  elif x == 'e':
    return '3'
  elif x == 'i':
    return '2'
  elif x == 'o':
    return '1'
  elif x == 'u':
    return '0'
  else:
    return x
     
def encodeName(x):
  return "".join(list(map(encode1aux, x)))
  
#11-Escreva uma função betterEncodeName :: String -> String que substitua vogais em uma string, conforme este esquema: a = 4, e = 3, i = 1, o = 0, u = 00.
def encode2aux(x):
  if x == 'a':
    return '4'
  elif x == 'e':
    return '3'
  elif x == 'i':
    return '1'
  elif x == 'o':
    return '0'
  elif x == 'u':
    return '00'
  else:
    return x

def betterEncodeName(x):
  return "".join(list(map(encode2aux, x)))
  
#12-Dada uma lista de strings, produzir outra lista com strings de 10 caracteres, usando o seguinte esquema: strings de entrada com mais de 10 caracteres são truncadas, strings com até 10 caracteres são completadas com '.' até ficarem com 10 caracteres.
def func(x):
  y = list(map(lambda x: x+"..........",x))
  z = list(map(lambda y: y[:10], y))
  return z
