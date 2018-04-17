repete(0, _, []).
repete(N, C, L) :- 
 N > 0,
 L = [C | T],
 N1 is N - 1,
 repete(N1, C, T).

% 1
zeroInit(L):-
    L = [0|_].

% 2
has5(L):-
    L = [_,_,_,_,_].

% 3
hasN(L,N):-
    length(L,N).
% 4
potN0(N,L):-
    H is 2^N,
    L = [H|T],
    N1 is N - 1,
    N1 >= -1,
    potN0(N1,T).
potN0(_,[]).

% 5
zipmult([],[],[]).
zipmult(L1,L2,L3) :-
    L1 = [H1 | T1],
    L2 = [H2 | T2],
    H3 is H1 * H2,
    L3 = [H3 | T3],
    zipmult(T1,T2,T3).

% 6
potencias(N,L):-
    potN0(N,H),
    reverse(H,L).

% 7
positivos([],[]).
positivos(L1,L2):-
    L1 = [H1|T1],
    L2 = [H2|T2],
    H1 > 0,
    H2 is H1,
    positivos(T1,T2).

positivos(L1,L2):-
    L1 = [_|T1],
    positivos(T1,L2).

% 8
mesmaPosicao(A,L1,L2):-
    nth0(X,L1,A),
    nth0(Y,L2,A),
    X==Y.

% 9
comissao(NP,LP,C):-
    
   
% 10
azulejos(0,0).
azulejos(NA, NQ):-
	X is sqrt(NA),
    Y is floor(X),
    Z is NA - Y*Y,
    azulejos(Z,T),
	NQ is T+1.

