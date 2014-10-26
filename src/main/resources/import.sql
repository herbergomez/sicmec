--------------------ESTADOS PARA USUARIOS Y PACIENTES -----------------
INSERT INTO sic_estado_usuario (descripcion)
		 VALUES ('Activo');
INSERT INTO sic_estado_usuario (descripcion)
		 VALUES ('Inactivo');
		 
INSERT INTO sic_estado_paciente (descripcion)
		 VALUES ('Activo');
INSERT INTO sic_estado_paciente (descripcion)
		 VALUES ('Inactivo');
INSERT INTO sic_estado_paciente (descripcion)
		 VALUES ('Muerte');
		 
--------------------DEPARTAMENTOS--------------------------------------
INSERT INTO sic_departamento(nombre_departamento)
		 VALUES ('Ahuachapán');
INSERT INTO sic_departamento( nombre_departamento)
		 VALUES ('Santa Ana');
INSERT INTO sic_departamento( nombre_departamento)
		 VALUES ('Sonsonate');

--------------------------MUNICIPIOS-----------------------------------
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Ahuachapán' , 1);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Jujutla' , 1);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Atiquizaya' , 1);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Concepción de Ataco' , 1);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('El Refugio', 1);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Guaymango' , 1);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Apaneca' , 1);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('San Francisco Menéndez' , 1);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('San Lorenzo' , 1);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('San Pedro Puxtla' , 1);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Tacuba' , 1);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Turín' , 1);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Candelaria de la Frontera' , 2);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Chalchuapa' , 2);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Coatepeque' , 2);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('El Congo' , 2);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('El Porvenir' , 2);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Masahuat' , 2);
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Metapán' , 2);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('San Antonio Pajonal' , 2);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('San Sebastián Salitrillo' , 2);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Santa Ana' , 2);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Santa Rosa Guachipilín' , 2);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Santiago de la Frontera' , 2);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Texistepeque' , 2);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Acajutla' , 3);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Armenia' , 3);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Caluco' , 3);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Cuisnahuat' , 3);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Izalco' , 3);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Juayúa' , 3);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Nahuizalco' , 3);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Nahulingo' , 3);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Salcoatitán' , 3);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('San Antonio del Monte' , 3);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('San Julián' , 3);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Santa Catarina Masahuat' , 3);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Santa Isabel Ishuatán' , 3);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Santo Domingo' , 3);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Sonsonate' , 3);	
INSERT INTO sic_municipio( nombre_municipio, fk_sic_departamento)
          VALUES ('Sonzacate' , 3);	
