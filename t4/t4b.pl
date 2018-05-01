/*
Fase 1 - Modalidade Iniciação, Nível 2, OBI2006

"Analistas de Sistemas"
Uma empresa esta instalada no primeiro, segundo, terceiro
e quarto andares de um predio de escritorios. Tres
analistas de sistemas, Alice, Bia e Clara, com cargos
de analista senior e analista junior, trabalham na empresa,
e devem ser alocadas nos andares de acordo com
as seguintes restricoes:
1 - nem todos os analistas tem o mesmo cargo;
2 - cada andar da empresa pode ter apenas uma analista
alocada;
3 - Alice e alocada no quarto andar;
4 - Bia e analista senior;
5 - Bia e alocada em algum andar acima do andar
de Clara;
6 - Se ha analista alocada no terceiro andar, entao
tal analista tem o mesmo cargo que a analista do
quarto andar.
*/

analistasenior(_).
analistajunior(_).

regra3(X):- X = [_,_,_,alice].

regra4(X):- analistasenior(X).

regra5(X):-
    nth1(Ibia,X,bia),
    nth1(Iclara,X,clara),
    Ibia > Iclara.

regra6(X):-
    (   nth1(3,X,bia) ->  analistasenior(alice) ; analistajunior(alice)).

analistas(X):-
    member(vazio,X),
    member(alice,X),
    member(bia,X),
    member(clara,X),
    
    regra3(X),
    regra4(bia),
    regra5(X),
    regra6(X).

/*
Questao 1. Qual das seguintes afirmacoes e necessariamente
falsa?
(A) Bia e alocada no segundo andar. - analistas([_,bia,_,_]).
(B) Bia e alocada no terceiro andar. - analistas([_,_,bia,_]).
(C) Clara e alocada no primeiro andar. - analistas([clara,_,_,_]).
(D) Clara e alocada no segundo andar. - analistas([_,clara,_,_]).
(E) Clara e alocada no terceiro andar. - analistas([_,_,clara,_]). <- Falsa (opção correta)
*/
