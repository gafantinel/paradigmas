%OBI2010

% Escola Colorida

/* 
Uma escola ocupa um predio com seis andares, numerados
de 1 a 6 (1 e o andar mais baixo, 6 o mais alto).
Os alunos e professores decidiram em conjunto que
cada andar sera pintado com uma cor diferente, entre
amarelo, branco, rosa, azul, laranja e verde. Ficou
tambem decidido que as seguintes condicoes devem ser
obedecidas:
• Branco deve ser usado para pintar ou o andar 3
ou o andar 4.
• O andar pintado de laranja e mais baixo do que
o andar pintado de rosa.
• O andar pintado de azul e mais baixo do que o
andar pintado de laranja, mas mais alto do que
o andar pintado de amarelo.
*/

/* Questao 1. Qual das alternativas abaixo e uma lista
correta de cores para os andares da escola, do andar 1
ao andar 6?
(A) amarelo, azul, branco, verde, laranja, rosa. - predio([amarelo, azul, branco, verde, laranja, rosa]). <- Correta
(B) amarelo, azul, laranja, rosa, branco, verde. - predio([amarelo, azul, laranja, rosa, branco, verde]).
(C) amarelo, laranja, branco, azul, rosa, verde. - predio([amarelo, laranja, branco, azul, rosa, verde]).
(D) azul, amarelo, laranja, branco, rosa, verde. - predio([azul, amarelo, laranja, branco, rosa, verde]).
(E) verde, amarelo, rosa, branco, azul, laranja. - predio([verde, amarelo, rosa, branco, azul, laranja]).
*/

regra1(X) :-
    nth1(3,X,branco),
    nth1(3,X,branco).

regra2(X):-
    nth1(L,X,laranja),
    nth1(R,X,rosa),
    L < R.

regra3(X):-
    nth1(A,X,azul),
    nth1(L,X,laranja),
    nth1(Am,X,amarelo),
    A < L,
    A > Am.

predio(X):-
    X = [_,_,_,_,_,_],
    member(branco,X),
    member(azul,X),
    member(amarelo,X),
    member(laranja,X),
    member(rosa,X),
    member(verde,X),
    
    regra1(X),
    regra2(X),
    regra3(X).
