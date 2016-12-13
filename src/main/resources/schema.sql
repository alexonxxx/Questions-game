/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     16/10/2016 12:46:41                          */
/*==============================================================*/


/*alter table PREGUNTA
   drop constraint FK_PREGUNTA__CATEGORI_CATEGORI;

alter table PREGUNTAPROPOSADA
   drop constraint FK_PREGUNTA_CATEGORIT_CATEGORI;

alter table PREGUNTAPROPOSADA
   drop constraint FK_PREGUNTA_PROPOSA_USUARI;

alter table PREGUNTESREPTE
   drop constraint FK_PREGUNTE_CATEGORIT_CATEGORI;

alter table PREGUNTESREPTE
   drop constraint FK_PREGUNTE_CONTE_PRE_REPTE;

alter table PUNTUACIO
   drop constraint FK_PUNTUACI_PERTANY_A_CATEGORI;

alter table PUNTUACIO
   drop constraint FK_PUNTUACI_RELATIONS_USUARI;

alter table RESPOSTA
   drop constraint FK_RESPOSTA__CONTESTA_PREGUNTA;

alter table RESPOSTAPROPOSADA
   drop constraint FK_RESPOSTA_CONTESTAD_PREGUNTA;

alter table RESPOSTAREPTE
   drop constraint FK_RESPOSTA__CONTESTA_PREGUNTE;

alter table USUARI
   drop constraint FK_USUARI_L_HA_JUGA_REPTE;

drop table CATEGORIA cascade constraints;

drop index _CATEGORITZADA_PER_FK;

drop table PREGUNTA cascade constraints;

drop index CATEGORITZADA_FK;

drop index PROPOSA_FK;

drop table PREGUNTAPROPOSADA cascade constraints;

drop index CONTE_PREGUNTES_FK;

drop index CATEGORITZADA_PER_2_FK;

drop table PREGUNTESREPTE cascade constraints;

drop index RELATIONSHIP_1_FK;

drop table PUNTUACIO cascade constraints;

drop table REPTE cascade constraints;

drop table RESPOSTA cascade constraints;

drop table RESPOSTAPROPOSADA cascade constraints;

drop table RESPOSTAREPTE cascade constraints;

drop index L_HA_JUGAT_FK;

drop table USUARI cascade constraints;*/

/*==============================================================*/
/* Table: CATEGORIA                                             */
/*==============================================================*/
create table CATEGORIA
(
   IDCATEGORIA          NUMBER               not null,
   NOMCATEGORIA         VARCHAR2(20)         not null,
   constraint PK_CATEGORIA primary key (IDCATEGORIA)
);

/*==============================================================*/
/* Table: PREGUNTA                                              */
/*==============================================================*/
create table PREGUNTA
(
   IDPREGUNTA           NUMBER               not null,
   IDCATEGORIA          NUMBER               not null,
   ENUNCIATPREGUNTA     VARCHAR2(240)        not null,
   constraint PK_PREGUNTA primary key (IDPREGUNTA)
);

/*==============================================================*/
/* Index: _CATEGORITZADA_PER_FK                                 */
/*==============================================================*/
create index _CATEGORITZADA_PER_FK on PREGUNTA (
   IDCATEGORIA ASC
);

/*==============================================================*/
/* Table: PREGUNTAPROPOSADA                                     */
/*==============================================================*/
create table PREGUNTAPROPOSADA
(
   NOMUSUARI            VARCHAR2(20)         not null,
   IDPREGUNTAPROPOSADA  NUMBER               not null,
   IDCATEGORIA          NUMBER               not null,
   ENUNCIATPROPOSADA    VARCHAR2(240)        not null,
   constraint PK_PREGUNTAPROPOSADA primary key (NOMUSUARI, IDPREGUNTAPROPOSADA)
);

/*==============================================================*/
/* Index: PROPOSA_FK                                            */
/*==============================================================*/
create index PROPOSA_FK on PREGUNTAPROPOSADA (
   NOMUSUARI ASC
);

/*==============================================================*/
/* Index: CATEGORITZADA_FK                                      */
/*==============================================================*/
create index CATEGORITZADA_FK on PREGUNTAPROPOSADA (
   IDCATEGORIA ASC
);

/*==============================================================*/
/* Table: PREGUNTESREPTE                                        */
/*==============================================================*/
create table PREGUNTESREPTE
(
   IDPREGUNTAREPTE      NUMBER               not null,
   IDCATEGORIA          NUMBER               not null,
   IDREPTE              NUMBER               not null,
   ENUNCIATPREGUNTAREPTE VARCHAR2(240)       not null,
   constraint PK_PREGUNTESREPTE primary key (IDPREGUNTAREPTE, IDREPTE)
);

/*==============================================================*/
/* Index: CATEGORITZADA_PER_2_FK                                */
/*==============================================================*/
create index CATEGORITZADA_PER_2_FK on PREGUNTESREPTE (
   IDCATEGORIA ASC
);

/*==============================================================*/
/* Index: CONTE_PREGUNTES_FK                                    */
/*==============================================================*/
create index CONTE_PREGUNTES_FK on PREGUNTESREPTE (
   IDREPTE ASC
);

/*==============================================================*/
/* Table: PUNTUACIO                                             */
/*==============================================================*/
create table PUNTUACIO
(
   IDCATEGORIA          NUMBER               not null,
   NOMUSUARI            VARCHAR2(20)         not null,
   PREGUNTESCORRECTES   NUMBER(4)            default 0,
   PREGUNTESINCORRECTES NUMBER(4)            default 0,
   PERCENTATGE          NUMBER(5,2)          default 0,
   constraint PK_PUNTUACIO primary key (IDCATEGORIA, NOMUSUARI),
   constraint AK_IDPUNTUACIO_PUNTUACI unique (IDCATEGORIA)
);

/*==============================================================*/
/* Index: RELATIONSHIP_1_FK                                     */
/*==============================================================*/
create index RELATIONSHIP_1_FK on PUNTUACIO (
   NOMUSUARI ASC
);

/*==============================================================*/
/* Table: REPTE                                                 */
/*==============================================================*/
create table REPTE
(
   IDREPTE              NUMBER               not null,
   PUNTUACIO            NUMBER(4)            default 0
      constraint CKC_PUNTUACIO_REPTE check (PUNTUACIO is null or (PUNTUACIO between 0 and 20)),
   constraint PK_REPTE primary key (IDREPTE)
);

/*==============================================================*/
/* Table: RESPOSTA                                              */
/*==============================================================*/
create table RESPOSTA
(
   IDPREGUNTA           NUMBER               not null,
   IDRESPOSTA           NUMBER               not null,
   TEXT                 VARCHAR2(240)        not null,
   CORRECTA             SMALLINT             not null,
   constraint PK_RESPOSTA primary key (IDPREGUNTA, IDRESPOSTA),
   constraint AK_IDRESPOSTA_RESPOSTA unique (IDPREGUNTA, IDRESPOSTA)
);

/*==============================================================*/
/* Table: RESPOSTAPROPOSADA                                     */
/*==============================================================*/
create table RESPOSTAPROPOSADA
(
   NOMUSUARI            VARCHAR2(20)         not null,
   IDPREGUNTAPROPOSADA  NUMBER               not null,
   IDRESPOSTAPROPOSADA  NUMBER               not null,
   TEXT                 VARCHAR2(240)        not null,
   CORRECTA             SMALLINT             not null,
   constraint PK_RESPOSTAPROPOSADA primary key (NOMUSUARI, IDPREGUNTAPROPOSADA, IDRESPOSTAPROPOSADA),
   constraint AK_IDRESPOSTAPROPOSAD_RESPOSTA unique (NOMUSUARI, IDPREGUNTAPROPOSADA, IDRESPOSTAPROPOSADA)
);

/*==============================================================*/
/* Table: RESPOSTAREPTE                                         */
/*==============================================================*/
create table RESPOSTAREPTE
(
   IDPREGUNTAREPTE      NUMBER               not null,
   IDRESPOSTAREPTE      NUMBER               not null,
   TEXT                 VARCHAR2(240)        not null,
   CORRECTA             SMALLINT             not null,
   constraint PK_RESPOSTAREPTE primary key (IDPREGUNTAREPTE, IDRESPOSTAREPTE),
   constraint AK_IDRESPOSTAREPTE2_RESPOSTA unique (IDPREGUNTAREPTE, IDRESPOSTAREPTE),
   constraint AK_IDRESPOSTAREPTE_RESPOSTA unique (IDPREGUNTAREPTE)
);

/*==============================================================*/
/* Table: USUARI                                                */
/*==============================================================*/
/*Canviar per projecte final*/
create table USUARI
(
   NOMUSUARI            VARCHAR2(20)         not null,
   IDREPTE              NUMBER,
   MAIL                 VARCHAR2(240)        ,
   CONTRASSENYA         VARCHAR2(240)        ,
   PUNTUACIOTOTAL       NUMBER(4)            default 0,
   PREGUNTESPROPOSADES  NUMBER(4)            default 0,
   PREGUNTESMODERADES   NUMBER(4)            default 0,
   constraint PK_USUARI primary key (NOMUSUARI)
);

/*==============================================================*/
/* Table:user_roles                                       */
/*==============================================================*/

CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES USUARI (NOMUSUARI));


/*==============================================================*/
/* Index: L_HA_JUGAT_FK                                         */
/*==============================================================*/



create index L_HA_JUGAT_FK on USUARI (
   IDREPTE ASC
);

alter table PREGUNTA
   add constraint FK_PREGUNTA__CATEGORI_CATEGORI foreign key (IDCATEGORIA)
      references CATEGORIA (IDCATEGORIA);

alter table PREGUNTAPROPOSADA
   add constraint FK_PREGUNTA_CATEGORIT_CATEGORI foreign key (IDCATEGORIA)
      references CATEGORIA (IDCATEGORIA);

alter table PREGUNTAPROPOSADA
   add constraint FK_PREGUNTA_PROPOSA_USUARI foreign key (NOMUSUARI)
      references USUARI (NOMUSUARI);

alter table PREGUNTESREPTE
   add constraint FK_PREGUNTE_CATEGORIT_CATEGORI foreign key (IDCATEGORIA)
      references CATEGORIA (IDCATEGORIA);

alter table PREGUNTESREPTE
   add constraint FK_PREGUNTE_CONTE_PRE_REPTE foreign key (IDREPTE)
      references REPTE (IDREPTE);

alter table PUNTUACIO
   add constraint FK_PUNTUACI_PERTANY_A_CATEGORI foreign key (IDCATEGORIA)
      references CATEGORIA (IDCATEGORIA);

alter table PUNTUACIO
   add constraint FK_PUNTUACI_RELATIONS_USUARI foreign key (NOMUSUARI)
      references USUARI (NOMUSUARI);

alter table RESPOSTA
   add constraint FK_RESPOSTA__CONTESTA_PREGUNTA foreign key (IDPREGUNTA)
      references PREGUNTA (IDPREGUNTA);

alter table RESPOSTAPROPOSADA
   add constraint FK_RESPOSTA_CONTESTAD_PREGUNTA foreign key (NOMUSUARI, IDPREGUNTAPROPOSADA)
      references PREGUNTAPROPOSADA (NOMUSUARI, IDPREGUNTAPROPOSADA);

alter table RESPOSTAREPTE
   add constraint FK_RESPOSTA__CONTESTA_PREGUNTE foreign key (IDPREGUNTAREPTE)
      references PREGUNTESREPTE (IDPREGUNTAREPTE);

alter table USUARI
   add constraint FK_USUARI_L_HA_JUGA_REPTE foreign key (IDREPTE)
      references REPTE (IDREPTE);

