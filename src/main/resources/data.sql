insert into player values (1,null,'admin','','$2a$04$XnhwasR32dlpAylvG4NhpeeNSDQzecy69hJ4QV1J3EnlbxcT0qFae','');
insert into contest values (1,'test1','test1');
insert into contest_player values (1,1);

select setval('hibernate_sequence', 100, true);