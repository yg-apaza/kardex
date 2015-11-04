/* TRIGGERS DE VALIDACION DE DATOS */

/* USUARIO UsrIde, UsrDni, UsrNom, UsrApe */

DELIMITER $$
CREATE TRIGGER TR_UsrInsDatInv
	BEFORE INSERT ON USUARIO FOR EACH ROW
	BEGIN
		IF LENGTH(NEW.UsrIde) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato invalido para identificador';
		ELSEIF LENGTH(NEW.UsrDni) <> 8 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato invalido para numero de DNI';
		ELSEIF LENGTH(NEW.UsrNom) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato invalido para nombres';
		ELSEIF LENGTH(NEW.UsrApe) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato invalido para apellidos';
		END IF;
	END;
$$

DELIMITER $$
CREATE TRIGGER TR_UsrModDatInv
	BEFORE UPDATE ON USUARIO FOR EACH ROW
	BEGIN
		IF LENGTH(NEW.UsrIde) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato invalido para identificador';
		ELSEIF LENGTH(NEW.UsrDni) <> 8 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato invalido para numero de DNI';
		ELSEIF LENGTH(NEW.UsrNom) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato invalido para nombres';
		ELSEIF LENGTH(NEW.UsrApe) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato invalido para apellidos';
		ELSEIF (SELECT COUNT(*) FROM USUARIO WHERE UsrEstReg = 1 AND UsrPer = 1) = 1 AND OLD.UsrPer = 1 AND NEW.UsrPer = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Debe haber al menos un administrador';
		END IF;
	END;
$$

/* UNIDAD UniCod UniDes*/

DELIMITER $$
CREATE TRIGGER TR_UniInsDatInv
	BEFORE INSERT ON UNIDAD FOR EACH ROW
	BEGIN
		IF LENGTH(NEW.UniDes) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato invalido para descripcion de unidad';       
		END IF;
	END;
$$

DELIMITER $$
CREATE TRIGGER TR_UniModDatInv
	BEFORE UPDATE ON UNIDAD FOR EACH ROW
	BEGIN
		IF LENGTH(NEW.UniDes) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato invalido para descripcion de unidad';
		END IF;
	END;
$$


/*ALMACEN AlmNom, AlmUbi*/

DELIMITER $$
CREATE TRIGGER TR_AlmInsDatInv
	BEFORE INSERT ON ALMACEN FOR EACH ROW
	BEGIN
		IF LENGTH(NEW.AlmNom) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato invalido para nombre de almacen';
		ELSEIF LENGTH(NEW.AlmUbi) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato invalido para ubicacion de almacen';
		END IF;
  END;
$$

DELIMITER $$
CREATE TRIGGER TR_AlmModDatInv
	BEFORE UPDATE ON ALMACEN FOR EACH ROW
	BEGIN
		IF LENGTH(NEW.AlmNom) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato invalido para nombre de almacen';
		ELSEIF LENGTH(NEW.AlmUbi) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato invalido para ubicacion de almacen';
		END IF;
  END;
$$

/*DOCUMENTO DocCod, DocNom, */

DELIMITER $$  
CREATE TRIGGER TR_DocInsDatInv
	BEFORE INSERT ON DOCUMENTO FOR EACH ROW
	BEGIN
		IF LENGTH(NEW.DocNom) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato invalido para nombre de documento';
		END IF;
  END;
$$

DELIMITER $$
CREATE TRIGGER TR_DocModDatInv
	BEFORE UPDATE ON DOCUMENTO FOR EACH ROW
	BEGIN
		IF LENGTH(NEW.DocNom) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato invalido para nombre de documento';
		END IF;
	END;
$$

/*PRODUCTO ProCod, ProNom*/

DELIMITER $$  
CREATE TRIGGER TR_ProInsDatInv
	BEFORE INSERT ON PRODUCTO FOR EACH ROW
	BEGIN
		IF LENGTH(NEW.ProNom) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato invalido para nombre de producto';
		END IF;
  END;
$$

DELIMITER $$
CREATE TRIGGER TR_ProModDatInv
	BEFORE UPDATE ON PRODUCTO FOR EACH ROW
	BEGIN
		IF LENGTH(NEW.ProNom) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato invalido para nombre de producto';
		END IF;
  END;
$$

/*KARDEX DETALLE Anio, Mes, Dia*/

DELIMITER $$
CREATE TRIGGER TR_KarDetDatInv
	BEFORE INSERT ON KARDEX_DET FOR EACH ROW
	BEGIN
		SET @numKarDet = (SELECT COUNT(*) FROM KARDEX_DET WHERE (AlmCod = NEW.AlmCod AND ProCod = NEW.ProCod));
		IF (NEW.KarDetAnio < 1990) THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Año invalido'; 
		ELSEIF (NEW.KarDetMes > 12 OR NEW.KarDetMes < 1) THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Mes invalido'; 
		ELSEIF (NEW.KarDetDia > 31 OR NEW.KarDetDia < 1) THEN
				SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dia invalido'; 
		ELSEIF (@numKarDet >= 1)	THEN
			SET @anio =(SELECT KarDetAnio FROM KARDEX_DET WHERE (AlmCod=NEW.AlmCod AND ProCod=NEW.ProCod) ORDER BY KarDetAnio DESC, KarDetMes DESC,KarDetDia DESC LIMIT 1);
			SET @mes = (SELECT KarDetMes FROM KARDEX_DET WHERE (AlmCod=NEW.AlmCod AND ProCod=NEW.ProCod) ORDER BY KarDetAnio DESC, KarDetMes DESC,KarDetDia DESC LIMIT 1);
			SET @dia = (SELECT KarDetDia FROM KARDEX_DET WHERE (AlmCod=NEW.AlmCod AND ProCod=NEW.ProCod) ORDER BY KarDetAnio DESC, KarDetMes DESC,KarDetDia DESC LIMIT 1);
			
			IF (NEW.KarDetAnio < @anio) THEN
				SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'El año debe de ser mayor al anterior detalle';
			ELSEIF (@anio = NEW.KarDetAnio AND  NEW.KarDetMes<@mes ) THEN
				SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'El mes debe de ser mayor al anterior detalle';
			ELSEIF (@anio = NEW.KarDetAnio AND @mes = NEW.KarDetMes AND  NEW.KarDetDia < @dia) THEN
				SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'El día debe de ser mayor al anterior detalle';
			END IF;
					
		END IF;
        
		IF NEW.KarDetOpe = 1 THEN
			SET @cantidad = (SELECT KarCan FROM KARDEX WHERE ProCod = NEW.ProCod AND AlmCod = NEW.AlmCod) + NEW.KarDetCan ;
            SET @valTot = (SELECT KarValTot FROM KARDEX WHERE ProCod = NEW.ProCod AND AlmCod = NEW.AlmCod) + NEW.KarDetValTot;
            SET @valUni = @valTot / @cantidad;
            IF (@valUni IS null) THEN
				SET @valUni = 0;
            END IF;
        ELSE
			SET @cantidad = (SELECT KarCan FROM KARDEX WHERE ProCod = NEW.ProCod AND AlmCod = NEW.AlmCod) - NEW.KarDetCan;
            SET @valTot = (SELECT KarValTot FROM KARDEX WHERE ProCod = NEW.ProCod AND AlmCod = NEW.AlmCod) - NEW.KarDetValTot ;
            SET @valUni = @valTot / @cantidad;
            IF (@valUni IS null) THEN
				SET @valUni = 0;
            END IF;
        END IF;
		
		IF(@cantidad < 0 OR @valTot < 0 OR @valUni < 0) THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Cantidad excedida';
    END IF;
	END;
$$


DELIMITER $$
CREATE TRIGGER TR_KarDetMod
	BEFORE UPDATE ON KARDEX_DET FOR EACH ROW
	BEGIN
		IF (NEW.KarDetAnio < 1990) THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Año invalido'; 
		ELSEIF (NEW.KarDetMes > 12 OR NEW.KarDetMes < 1) THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Mes invalido'; 
		ELSEIF (NEW.KarDetDia > 31 OR NEW.KarDetDia < 1) THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dia invalido'; 
		ELSEIF (OLD.KarDetAnio > NEW.KarDetAnio) THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'El año debe de ser mayor al detalle anterior';
		ELSEIF (OLD.KarDetAnio = NEW.KarDetAnio AND OLD.KarDetMes > NEW.KarDetMes) THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'El mes debe de ser mayor al detalle anterior';
		ELSEIF (OLD.KarDetAnio = NEW.KarDetAnio AND OLD.KarDetMes = NEW.KarDetMes AND OLD.KarDetDia > NEW.KarDetDia) THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'El día debe de ser mayor al detalle anterior';
		END IF;	
		
		IF NEW.KarDetEstReg = 1 THEN
			IF OLD.KarDetOpe = 1 THEN
				SET @cantidad = (SELECT KarCan FROM KARDEX WHERE ProCod = NEW.ProCod AND AlmCod = NEW.AlmCod) - OLD.KarDetCan;
				SET @valTot = (SELECT KarValTot FROM KARDEX WHERE ProCod = NEW.ProCod AND AlmCod = NEW.AlmCod) - OLD.KarDetValTot;
				SET @valUni = @valTot/@cantidad;
				IF (@valUni is NULL) THEN
					SET @valUni = 0;
				END IF;
			ELSE
				SET @cantidad = (SELECT KarCan FROM KARDEX WHERE ProCod = NEW.ProCod AND AlmCod = NEW.AlmCod) + OLD.KarDetCan;
				SET @valTot = (SELECT KarValTot FROM KARDEX WHERE ProCod = NEW.ProCod AND AlmCod = NEW.AlmCod) + OLD.KarDetValTot;
				SET @valUni = @valTot/@cantidad;
				IF (@valUni = null) THEN
					SET @valUni = 0;
				END IF;
			END IF;
			
            IF NEW.KarDetOpe = 1 THEN
				SET @cantidad = @cantidad + NEW.KarDetCan;
                SET @valTot = @valTot + NEW.KarDetValTot;
                SET @valUni = @valTot / @cantidad;
                IF (@valUni is NULL) THEN
					SET @valUni = 0;
				END IF;
			ELSE
				SET @cantidad = @cantidad - NEW.KarDetCan;
                SET @valTot = @valTot - NEW.KarDetValTot;
                SET @valUni = @valTot / @cantidad;
                IF (@valUni is NULL) THEN
					SET @valUni = 0;	
				END IF;
            END IF;
            
			IF(@cantidad < 0 OR @valTot < 0 OR @valUni < 0) THEN
				SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Cantidad excedida';
			END IF;
    END IF;
	END;
$$

/* TRIGGER ACTUALIZAR KARDEX_CAB */

DELIMITER $$
CREATE TRIGGER TR_KarCabAct
	AFTER INSERT ON KARDEX_DET FOR EACH ROW
	BEGIN
		UPDATE KARDEX SET KarCan = NEW.KarDetSalCan, KarValUni = NEW.KarDetSalValUni, KarValTot = NEW.KarDetSalValTot WHERE ProCod = New.ProCod AND AlmCod = NEW.AlmCod;
	END;
$$

DELIMITER $$
CREATE TRIGGER TR_KarCabActMod
	AFTER UPDATE ON KARDEX_DET FOR EACH ROW
	BEGIN
		IF NEW.KarDetEstReg = 1 THEN
			UPDATE KARDEX SET KarCan = NEW.KarDetSalCan, KarValUni = NEW.KarDetSalValUni, KarValTot = NEW.KarDetSalValTot WHERE ProCod = New.ProCod AND AlmCod = NEW.AlmCod;
        ELSEIF NEW.KarDetEstReg = 3 THEN
			SET @cantidad = 0;
			SET @valTot = 0;
			SET @valUni = 0;
			IF OLD.KarDetOpe = 1 THEN
				SET @cantidad = (SELECT KarCan FROM KARDEX WHERE ProCod = NEW.ProCod AND AlmCod = NEW.AlmCod) - OLD.KarDetCan;
				SET @valTot = (SELECT KarValTot FROM KARDEX WHERE ProCod = NEW.ProCod AND AlmCod = NEW.AlmCod) - OLD.KarDetValTot;
				SET @valUni = @valTot/@cantidad;
				IF (@valUni IS null) THEN
					SET @valUni = 0;
				END IF;
			ELSE
				SET @cantidad = (SELECT KarCan FROM KARDEX WHERE ProCod = NEW.ProCod AND AlmCod = NEW.AlmCod) + OLD.KarDetCan;
				SET @valTot = (SELECT KarValTot FROM KARDEX WHERE ProCod = NEW.ProCod AND AlmCod = NEW.AlmCod) + OLD.KarDetValTot;
				SET @valUni = @valTot/@cantidad;
				IF (@valUni IS null) THEN
					SET @valUni = 0;
				END IF;
            END IF;
            UPDATE KARDEX SET KarCan = @cantidad, KarValUni = @valUni, KarValTot = @valTot WHERE ProCod = New.ProCod AND AlmCod = NEW.AlmCod;
    END IF;
	END;
$$
