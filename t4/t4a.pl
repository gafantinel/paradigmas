/*
Uma banda formada por alunos e alunas da escola esta gravando um CD com exatamente sete musicas
distintas – S, T, V, W, X, Y e Z. Cada musica ocupa exatamente uma das sete faixas contidas no
CD. Algumas das musicas sao sucessos antigos de rock; outras sao composicoes da propria banda. As
seguintes restricoes devem ser obedecidas:
1 - S ocupa a quarta faixa do CD.
2 - Tanto W como Y precedem S no CD (ou seja, W e Y estao numa faixa que e tocada antes de S
no CD).
3 - T precede W no CD (ou seja, T esta numa faixa que e tocada antes de W).
4 - Um sucesso de rock ocupa a sexta faixa do CD.
5 - Cada sucesso de rock e imediatamente precedido no CD por uma composicao da banda (ou seja,
no CD cada sucesso de rock toca imediatamente apos uma composicao da banda).
6 - Z e um sucesso de rock
*/

autoral(_).
sucesso(_).

regra1(X):- X = [_,_,_,s,_,_,_].

regra2(X):-
    nth0(Iw,X,w),
    nth0(Iy,X,y),
    Iw < 3, Iy < 3.

regra3(X):-
    nth0(Iw,X,w),
    nth0(It,X,t),
    Iw < 3,
    It < Iw.

regra4(X):-
    nth0(5,X,Sucesso),
    sucesso(Sucesso).

regra5(X):-
    autoral(A),
    sucesso(B),
    nextto(A,B,X).


cd(X):-
    X = [_,_,_,_,_,_,_],
    
    member(s,X),
    member(t,X),
    member(v,X),
    member(w,X),
    member(x,X),
    member(y,X),
    member(z,X),
    
    regra1(X),
    regra2(X),
    regra3(X),
    regra4(X),
	regra5(X).

/*
Questao 11. Qual das seguintes alternativas poderia
ser a ordem das musicas no CD, da primeira
para a setima faixa?
(A) T, W, V, S, Y, X, Z - cd([t,w,v,s,y,x,z])
(B) V, Y, T, S, W, Z, X - cd([v,y,t,s,w,z,x])
(C) X, Y, W, S, T, Z, S - cd([x,y,w,s,t,z,s])
(D) Y, T, W, S, X, Z, V - cd([y,t,w,s,x,z,v]) <- Correta
(E) Z, T, X, W, V, Y, S - cd([z,t,x,w,v,y,s])
*/
