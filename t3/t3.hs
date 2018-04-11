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
zipmult(L1,L2,L3):-
    
