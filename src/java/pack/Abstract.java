/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import objects.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * Класс, предоставляющий подключения к БД для тех, кто от него наследуется.
 * 
 * Также содержит методы для создания DTO-объектов на основе результатов
 * запроса к БД (ResultSet-а). 
 * 
 * ВАЖНО: если не указано иначе, то заголовки столбцов
 * в ResultSet-е должны соответствовать заголовкам из БД (можно посмотреть в 
 * папке sql scripts-> Структура.sql)
 * 
 * В ResultSet-e ОБЯЗАТЕЛЬНО должна быть выбрана нужная строка (при помощи rs.next())
 * @author Ольга
 */
public abstract class Abstract {

    private DataSource sour;

    public Abstract(DataSource sour) {
        this.sour = sour;
    }

    /**
     * Получение соединения к БД.
     * @return соединение с БД.
     * @throws SQLException 
     */
    protected Connection getConn() throws SQLException {
        return sour.getConnection();
    }

    /**
     * Создание объекта Service на основе результата запроса.
     * @param rs результат запроса
     * @param typeService тип сервиса
     * @return сервис со всеми заполненными полями
     * @throws SQLException 
     */
    protected Service makeService(ResultSet rs, TypeService typeService) throws SQLException {
        Service service = new Service();
        service.setCost(rs.getDouble("cost"));
        service.setIdService(rs.getInt("ID_Service"));
        service.setNameService(rs.getString("name_service"));
        service.setTypeService(typeService);
        return service;
    }

    /**
     * Создание объекта Sim на основе результата запроса.
     * @param rs результат запроса
     * @param tariff тариф
     * @return сим-карту со всеми заполненными полями
     * @throws SQLException 
     */
    protected Sim makeSim(ResultSet rs, Tariff tariff) throws SQLException {
        Sim sim = new Sim();
        sim.setSimId(rs.getInt("sim_id"));
        sim.setTariff(tariff);
        sim.setAccount(rs.getDouble("account"));
        return sim;
    }

    /**
     * Создание объекта Tariff на основе результата запроса.
     * @param rs результат запроса
     * @return тариф со всеми заполненными полями
     * @throws SQLException 
     */
    protected Tariff makeTariff(ResultSet rs) throws SQLException {
        Tariff tariff = new Tariff();
        tariff.setIdTariff(rs.getInt(" ID_tariff"));
        tariff.setNameTariff(rs.getString("name_tariff"));
        tariff.setDescription(rs.getString("description"));
        return tariff;
    }

    /**
     * Создание объекта TypeService на основе результата запроса.
     * @param rs результат запроса
     * @return тип услуги со всеми заполненными полями
     * @throws SQLException 
     */
    protected TypeService makeTypeService(ResultSet rs) throws SQLException {
        TypeService type = new TypeService();
        type.setIdType(rs.getInt("ID_type"));
        type.setNameType(rs.getString("name_type"));
        type.setMeasure(rs.getString("measure"));
        return type;
    }

    /**
     * Создание объекта Client на основе результата запроса.
     * @param rs результат запроса
     * @return клиента со всеми заполненными полями
     * @throws SQLException 
     */
    protected Client makeClient(ResultSet rs) throws SQLException {
        Client newClient = new Client();
        newClient.setСlientID(rs.getInt("client_id"));
        newClient.setPassportSeries(rs.getInt("passport_series"));
        newClient.setPassportNumber(rs.getInt("passport_number"));
        newClient.setFirstname(rs.getString("firstname"));
        newClient.setLastname(rs.getString("lastname"));
        newClient.setMiddlename(rs.getString("middlename"));
        newClient.setTelephoneNumber(rs.getLong("telephone_number"));
        return newClient;
    }

    /**
     * Создание объекта ClientContr на основе результата запроса.
     * @param rs результат запроса
     * @param client клиент
     * @param sim сим-карта
     * @return договор с клиентом со всеми заполненными полями
     * @throws SQLException 
     */
    protected ClientContr makeClientContr(ResultSet rs, Client client, Sim sim) throws SQLException {
        ClientContr newContr = new ClientContr();
        newContr.setContrID(rs.getInt("contr_id"));
        newContr.setClient(client);
        newContr.setSim(sim);
        newContr.setContrDoc(rs.getString("contr_doc"));
        newContr.setBeginDate(rs.getDate("begin_date"));
        return newContr;
    }

    /**
     * Создание объекта LegalContr на основе результата запроса.
     * @param rs результат запроса
     * @param legalEntity юридическое лицо
     * @return договор с юридическим лицом со всеми заполненными полями
     * @throws SQLException 
     */
    protected LegalContr makeLegalContr(ResultSet rs, LegalEntity legalEntity) throws SQLException {
        LegalContr newContr = new LegalContr();
        newContr.setLegalEntity(legalEntity);
        newContr.setContrID(rs.getInt("contr_id"));
        newContr.setContrDoc(rs.getString("contr_doc"));
        newContr.setBeginDate(rs.getDate("begin_date"));
        return newContr;
    }

    /**
     * Создание объекта LegalEntity на основе результата запроса.
     * @param rs результат запроса
     * @return юридическое лицо со всеми заполненными полями
     * @throws SQLException 
     */
    protected LegalEntity makeLegalEntity(ResultSet rs) throws SQLException {
        LegalEntity newEntity = new LegalEntity();
        newEntity.setCompanyID(rs.getInt("company_id"));
        newEntity.setNameCompany(rs.getString("name_company"));
        newEntity.setAddress(rs.getString("address"));
        newEntity.setTelephone(rs.getString("telephone"));
        newEntity.setEmail(rs.getString("e_mail"));
        newEntity.setDetails(rs.getString("details"));
        return newEntity;
    }

    /**
     * Создание объекта PhoneNumber на основе результата запроса.
     * @param rs результат запроса
     * @param sim сим-карта
     * @return номер телефона со всеми заполненными полями
     * @throws SQLException 
     */
    protected PhoneNumber makePhoneNumber(ResultSet rs, Sim sim) throws SQLException {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setSim(sim);
        phoneNumber.setNumber(rs.getLong("phone_number"));
        return phoneNumber;
    }
    
    /**
     * Создание объекта User на основе результата запроса.
     * @param rs результат запроса
     * @param role роль
     * @return пользователя со всеми заполненными полями
     * @throws SQLException 
     */
    protected User makeUser(ResultSet rs, Role role) throws SQLException {
        User user = new User();
        user.setRole(role);
        user.setIdUser(rs.getInt("id_user"));
        user.setUserName(rs.getString("user_name"));
        user.setUserPassword(rs.getString("user_password"));
        return user;
    }
    
    /**
     * Создание объекта Role на основе результата запроса.
     * @param rs результат запроса
     * @return роль со всеми заполненными полями
     * @throws SQLException 
     */
    protected Role makeRole(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setIdRole(rs.getInt("id_role"));
        role.setRoleName(rs.getString("role_name"));
        role.setReadOnly(rs.getBoolean("read_only"));
        return role;
    }
}
