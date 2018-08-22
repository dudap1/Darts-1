
insert into player values (1,null,false,'admin','','$2a$07$qT0PeJq4lcxDfexyhylYyOYLXWyKuABuVq0xy2dvMQUYIWhzhboUy','');
insert into player values (2,null,false,'user','','$2a$07$qT0PeJq4lcxDfexyhylYyOYLXWyKuABuVq0xy2dvMQUYIWhzhboUy','');
insert into players_role VALUES (1,'ROLE_ADMIN');
insert into players_role VALUES (1,'ROLE_USER');
insert into players_role VALUES (2,'ROLE_USER');
insert into contest values (1,'test1','test',null);
insert into contest values (2,'test221','test',null);
insert into contest_player values (1,1);
insert into round values (1,30,30,null,1,2);
insert into round values (2,30,60,null,1,1);
insert into round values (3,30,90,null,1,1);

select setval('hibernate_sequence', 100, true);