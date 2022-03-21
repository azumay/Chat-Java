-- DATABASE CHAT

drop database if exists chat;
create database chat;
use chat;

-- USER ------------------------------------------------------------------------

-- Para abrir las conexiones remotas (En XAMPP no hace falta tocar nada )

-- 1. editar el fichero /etc/mysql/my.cnf  ( para Xampp es /opt/lampp/etc/my.cnf )
-- 2. añadir una linea con la IP del servidor bind-address = 192.168.8.80
-- 	/etc/mysql/mysql.conf.d/mysqld.cnf  (depenent de la versió)
--    después de bind-address = 127.0.0.1
-- 3. reiniciar el servidor mysql : service mysql restart

drop user 'appuser'@'%';
drop user 'appuser'@'localhost';

create user 'appuser'@'%' identified by 'TiC.123456';
grant execute on *.* to 'appuser'@'%';
grant select on mysql.proc to 'appuser'@'%';

create user 'appuser'@'localhost' identified by 'TiC.123456';
grant execute on *.* to 'appuser'@'localhost';
grant select on mysql.proc to 'appuser'@'localhost';


-- TABLES ----------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `users` (
  `nick` varchar(50) NOT NULL,
  `userhost` varchar(50) DEFAULT NULL,
  `date_con` datetime DEFAULT NULL,
  `last_read` int(11) NOT NULL,
  PRIMARY KEY (`nick`),
  UNIQUE KEY `userhost` (`userhost`)
);

CREATE TABLE IF NOT EXISTS `messages` (
  `id_message` int(11) NOT NULL AUTO_INCREMENT,
  `nick` varchar(50) NOT NULL,
  `message` varchar(255) NOT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_message`)
);



-- PROCEDIMIENTOS --------------------------------------------------------------

delimiter $$
create procedure connect( pnick varchar(50) )
begin
  declare vid_message int(11);
  if exists ( select nick from users where userhost=user())
  then
    signal sqlstate 'HY000' set message_text = 'Yet connected';
  else
    if exists ( select nick from users where nick=pnick  )
    then
      signal sqlstate 'HY000' set message_text = 'Nick already in use';
    else
      select MAX(id_message) into vid_message from messages;
      if(vid_message IS NULL)
      then
          set vid_message = 0;
      end if;
      insert into users(nick,userhost, date_con, last_read) values(pnick, user(), now(), vid_message);
    end if;
  end if;
end$$

create procedure send( msg VARCHAR(255) )
begin 
  declare vnick VARCHAR(50);
  select nick into vnick from users where userhost=user();
  if ( vnick is not null )
  then
    insert into messages(nick,message) values(vnick,msg);
  else
    signal sqlstate 'HY000' set message_text = 'Not connected.';    
  end if;        
end$$


create procedure getConnectedUsers()
begin
  select nick,date_con from users;
end$$


delimiter $$
create procedure getMessages()
begin
  declare vnick VARCHAR(50);
  declare vid_message int(11);
  select nick into vnick from users where userhost=user();
  if ( vnick is not null )
  then
    
    select nick,message,ts from messages where id_message > (select last_read from users where nick=vnick);
    
    select MAX(id_message) into vid_message from messages; 
    if( vid_message is not null)
    then
    	update users set last_read = vid_message where nick=vnick;   
    end if;
    
  else
    signal sqlstate 'HY000' set message_text = 'Not connected.'; 
  end if;
end$$

create procedure disconnect()
begin
  delete from users where userhost = user();
end$$

delimiter ;

-- EJEMPLO DE USO DE LOS PROCEDIMIENTOS ----------------------------------------

-- Conexion de usuarios

call connect ('pitufina');


-- Conseguir lista de usuarios conectados 

call getConnectedUsers();


-- Enviar un mensaje. Es necesario que el usuario esté conectado.

call send("Holaaaaaa!!!");

-- Leer mensajes

call getMessages();

-- Desconexion

call disconnect();
