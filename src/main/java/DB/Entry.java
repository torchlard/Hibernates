package DB;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.StandardBasicTypes;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.select.WithItem;
import net.sf.jsqlparser.util.TablesNamesFinder;
import net.sf.jsqlparser.util.deparser.ExpressionDeParser;

class dto {
  public Integer id;
  public String name;
}

public class Entry {
  private static SessionFactory factory;

  static {
    if (factory == null){
      Configuration config = new Configuration();
      Properties prop = new Properties();
      prop.put(Environment.HBM2DDL_AUTO , "update");
      prop.put(Environment.DIALECT , "org.hibernate.dialect.MariaDBDialect" );
      prop.put(Environment.URL , "jdbc:mariadb://localhost:3306/testdb" );
      prop.put(Environment.USER, "root" );
      prop.put(Environment.PASS, "666666");
      prop.put(Environment.DRIVER, "org.mariadb.jdbc.Driver");
      prop.put(Environment.AUTOCOMMIT, "true");
      prop.put(Environment.SHOW_SQL, "true");
  
      config.setProperties(prop);
      config.addAnnotatedClass(Teacher.class);
      StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
      factory = config.buildSessionFactory(ssr);
    }
  }



  public static void main(String[] args) {

    // StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    // Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
    // SessionFactory factory = meta.getSessionFactoryBuilder().build();
    Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    System.out.println("=======");
    
    // Teacher t1 = new Teacher(106,"pets","F",1.2f,24, new BigInteger("312"));
    // Integer id = (Integer)session.save(t1);
    // System.out.println("id added: "+id);

    // List<Teacher> people = session.createQuery("FROM Teacher").list();
    // people.forEach(i -> {
    //   System.out.print("name: "+ i.getName());
    //   System.out.print(", sex:"+ i.getSex());
    //   System.out.println(", num: "+ i.getNum());
    // });

    // String query = "select sum(t.num), t.sex from Teacher t where t.age>60 group by sex";
    // session.createQuery(query).list().forEach(System.out::println);

    // Teacher t = (Teacher)session.get(Teacher.class, 106);
    // t.setName("npvs");
    // t.setSex("M");
    // session.saveOrUpdate(t1);
    
    // Teacher t = (Teacher)session.get(Teacher.class, 106);
    // session.delete(t);

    // String hql = "update Teacher set name=:name where id=:ab";
    // Query query = session.createQuery(hql);
    // query.setParameter("name", "klj");
    // query.setParameter("ab", 2);
    // int result = query.executeUpdate();
    // System.out.println("result: "+result);
    
    // Criteria cr = session.createCriteria(Teacher.class);
    // Criterion age = Restrictions.gt("age", 50);
    // Criterion num = Restrictions.lt("num", 80f);
    // cr.add(Restrictions.or(
    //   Restrictions.and(age,num), Restrictions.like("name", "a%")
    // ));
    // cr.list().forEach(System.out::println);
      
    // CriteriaBuilder cb = session.getCriteriaBuilder();
    // CriteriaQuery<Teacher> cr = cb.createQuery(Teacher.class);
    // Root<Teacher> t = cr.from(Teacher.class);
    // cr.select(t)
    //   .where(cb.or(
    //     cb.and(
    //       cb.gt(t.get("num"), 30),
    //       cb.lt(t.get("age"), 70)
    //     ),
    //     cb.like(t.get("name"), "a%")
    //   )
    // );
    // session.createQuery(cr).getResultList().forEach(System.out::println);

    
    // String query = "select id,name from teacher where age>50";
    String query = "select id as i,name from teacher where age>50";
    // List<Object[]> res = session.createNativeQuery(query).getResultList();
    
    // List ll = new ArrayList<>();
    // res.forEach(i -> {
    //   Map<String,Object> map = new HashMap<>();
    //   map.put("id", i[0]);
    //   map.put("name", i[1]);
    //   ll.add(map);
    // });

    // ll.forEach(System.out::println);
    
    try {
      Select select = (Select) CCJSqlParserUtil.parse(query);
      // TablesNamesFinder finder = new TablesNamesFinder();
      // List<String> tableList = finder.getTableList(stmt);
      // tableList.forEach(System.out::println);
      
      // PlainSelect plain = (PlainSelect)select.getSelectBody();
      // List<SelectItem> selectItems = plain.getSelectItems();
      // System.out.println(selectItems.size());

      // for(int i=0; i<selectItems.size(); i++){
      //   SelectItem expr = ((SelectItem) selectItems.get(i));
      //   if (expr instanceof SelectExpressionItem){
      //     SelectExpressionItem col = (SelectExpressionItem) expr;
      //     // if (col.getAlias() != null){
      //     //   System.out.println(col.getAlias());
      //     // } else {
      //     // }
      //     System.out.println(col);
      //   }
      // }

      // for(int i=0;i<selectItems.size();i++){

      //   SelectItem item = selectItems.get(i);
      //   System.out.println("item: "+item);
      //   if (item instanceof AllColumns){

      //   } else {
      //     Expression expression=((SelectExpressionItem) item ).getExpression();  
      //     System.out.println("expr: "+expression);
      //     if( expression instanceof Column){
      //         Column col=(Column)expression;
      //         System.out.println(col.getColumnName());      
      //     } else if (expression instanceof Function){
      //     }
      //   }

      // }

      JSQLTools jsqltools = new JSQLTools();
      List<NaviSoftRealMeta> fieldlist = jsqltools.getColumnListTest(select);
      for (int i = 0; i < fieldlist.size(); i++) {
          System.out.println(fieldlist.get(i).getFieldName() + " " + fieldlist.get(i).getAliasFieldName());
      }
      

    } catch (JSQLParserException e){
      e.printStackTrace();
    }

    
    
    
    
    System.out.println("=======");
    tx.commit();
    session.close();
    factory.close();
  }  
}









