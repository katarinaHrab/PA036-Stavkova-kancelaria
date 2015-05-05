       
CREATE USER pa036  WITH ENCRYPTED PASSWORD 'pa036';
GRANT USAGE ON SCHEMA public to pa036;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT ON TABLES TO pa036;


GRANT CONNECT ON DATABASE pa036 to pa036;
GRANT USAGE ON SCHEMA public to pa036; 
GRANT SELECT ON ALL SEQUENCES IN SCHEMA public TO pa036;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO pa036;


CREATE TABLE "user" (Id  SERIAL NOT NULL, Login varchar(50) NOT NULL UNIQUE, Password varchar(100) NOT NULL, Name varchar(50) NOT NULL, Surname varchar(50) NOT NULL, DateOfBirth date NOT NULL, DateLastLogin date NOT NULL, Balance float8 NOT NULL, PRIMARY KEY (Id));
CREATE TABLE Role (Id  SERIAL NOT NULL, Name varchar(50) NOT NULL UNIQUE, PRIMARY KEY (Id));
CREATE TABLE Event (Id  SERIAL NOT NULL, Name varchar(50) NOT NULL, Place varchar(50) NOT NULL, Date date NOT NULL, DrawOdds float8 NOT NULL, LeagueId int4 NOT NULL, PRIMARY KEY (Id));
CREATE TABLE Ticket (Id  SERIAL NOT NULL, UserId int4 NOT NULL, DateOfCreated date NOT NULL, Deposit float8 NOT NULL, StatusId int4 NOT NULL, DateOfClosed date, PRIMARY KEY (Id));
CREATE TABLE User_Role (UserId int4 NOT NULL, RoleId int4 NOT NULL, PRIMARY KEY (UserId, RoleId));
CREATE TABLE Ticket_Event (TicketId int4 NOT NULL, EventId int4 NOT NULL, BetValue float8 NOT NULL, CompetitorId int4, PRIMARY KEY (TicketId, EventId));
CREATE TABLE Status (Id  SERIAL NOT NULL, Name varchar(50) NOT NULL UNIQUE, PRIMARY KEY (Id));
CREATE TABLE Sport (Id  SERIAL NOT NULL, KindOfSport varchar(50) NOT NULL, PRIMARY KEY (Id));
CREATE TABLE Competitor (Id  SERIAL NOT NULL, Name varchar(50) NOT NULL, CountryId int4 NOT NULL, SportId int4 NOT NULL, PRIMARY KEY (Id));
CREATE TABLE Event_Competitor (EventId int4 NOT NULL, CompetitorId int4 NOT NULL, Odds float8 NOT NULL, PRIMARY KEY (EventId, CompetitorId));
CREATE TABLE Contact (Id  SERIAL NOT NULL, Type varchar(50) NOT NULL, Value varchar(50) NOT NULL, PRIMARY KEY (Id));
CREATE TABLE Contact_User (ContactId int4 NOT NULL, UserId int4 NOT NULL, PRIMARY KEY (ContactId, UserId));
CREATE TABLE User_Favorite_Sport (UserId int4 NOT NULL, SportId int4 NOT NULL, Priority int4 NOT NULL, PRIMARY KEY (UserId, SportId));
CREATE TABLE League (Id  SERIAL NOT NULL, SportId int4 NOT NULL, Name varchar(50) NOT NULL, CountryId int4 NOT NULL, PRIMARY KEY (Id));
CREATE TABLE Country (Id  SERIAL NOT NULL, Name varchar(50) NOT NULL, PRIMARY KEY (Id));
ALTER TABLE User_Role ADD CONSTRAINT FKUser_Role973382 FOREIGN KEY (UserId) REFERENCES "user" (Id);
ALTER TABLE User_Role ADD CONSTRAINT FKUser_Role494787 FOREIGN KEY (RoleId) REFERENCES Role (Id);
ALTER TABLE Ticket ADD CONSTRAINT FKTicket359147 FOREIGN KEY (UserId) REFERENCES "user" (Id);
ALTER TABLE Ticket_Event ADD CONSTRAINT FKTicket_Eve928392 FOREIGN KEY (TicketId) REFERENCES Ticket (Id);
ALTER TABLE Ticket_Event ADD CONSTRAINT FKTicket_Eve356509 FOREIGN KEY (EventId) REFERENCES Event (Id);
ALTER TABLE Ticket ADD CONSTRAINT FKTicket599973 FOREIGN KEY (StatusId) REFERENCES Status (Id);
ALTER TABLE Event_Competitor ADD CONSTRAINT FKEvent_Comp115055 FOREIGN KEY (EventId) REFERENCES Event (Id);
ALTER TABLE Event_Competitor ADD CONSTRAINT FKEvent_Comp724153 FOREIGN KEY (CompetitorId) REFERENCES Competitor (Id);
ALTER TABLE Contact_User ADD CONSTRAINT FKContact_Us377803 FOREIGN KEY (ContactId) REFERENCES Contact (Id);
ALTER TABLE Contact_User ADD CONSTRAINT FKContact_Us191454 FOREIGN KEY (UserId) REFERENCES "user" (Id);
ALTER TABLE User_Favorite_Sport ADD CONSTRAINT FKUser_Favor292752 FOREIGN KEY (UserId) REFERENCES "user" (Id);
ALTER TABLE User_Favorite_Sport ADD CONSTRAINT FKUser_Favor180493 FOREIGN KEY (SportId) REFERENCES Sport (Id);
ALTER TABLE League ADD CONSTRAINT FKLeague67023 FOREIGN KEY (SportId) REFERENCES Sport (Id);
ALTER TABLE Competitor ADD CONSTRAINT FKCompetitor436659 FOREIGN KEY (CountryId) REFERENCES Country (Id);
ALTER TABLE Event ADD CONSTRAINT FKEvent649720 FOREIGN KEY (LeagueId) REFERENCES League (Id);
ALTER TABLE League ADD CONSTRAINT FKLeague745121 FOREIGN KEY (CountryId) REFERENCES Country (Id);
ALTER TABLE Competitor ADD CONSTRAINT FKCompetitor375485 FOREIGN KEY (SportId) REFERENCES Sport (Id);
ALTER TABLE Ticket_Event ADD CONSTRAINT FKTicket_Eve482699 FOREIGN KEY (CompetitorId) REFERENCES Competitor (Id);


GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO pa036;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO pa036;


INSERT INTO "user"(
            id, login, password, name, surname, dateofbirth, 
            datelastlogin, balance)
    VALUES 
            (1,'admin','36f17c3939ac3e7b2fc9396fa8e953ea','Admin','Admin','1980-01-01','1980-01-01',0);

INSERT INTO Role(
            id, name)
    VALUES (1, 'Superadmin');

INSERT INTO User_role(
            userid, roleid)
    VALUES (1, 1);
    
INSERT INTO country(
            id, name)
    VALUES (1, 'Cesko');
INSERT INTO country(
            id, name)
    VALUES (2, 'Slovensko');

INSERT INTO sport(
            id, kindofsport)
    VALUES (1, 'Fotbal');
INSERT INTO sport(
            id, kindofsport)
    VALUES (2, 'Hokej');

INSERT INTO status(
            id, name)
    VALUES (1, 'Open');
INSERT INTO status(
            id, name)
    VALUES (2, 'Closed');
INSERT INTO status(
            id, name)
    VALUES (3, 'Winning');
INSERT INTO status(
            id, name)
    VALUES (4, 'Losing');
