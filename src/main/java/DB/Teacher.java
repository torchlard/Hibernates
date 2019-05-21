package DB;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@Entity
@Table(name="teacher")
@SqlResultSetMapping(name="tMap", classes=
  @ConstructorResult(targetClass = dto.class,
    columns={
      @ColumnResult(name="id"),
      @ColumnResult(name="name")
    }
  ))
public class Teacher {

  @Id @GeneratedValue
  @Column(name="id")
  private Integer id;
  private String name;
  private String sex;
  private float num;
  private int age;
  @Column(name="largeNum")
  private BigInteger largeNum;

  public Integer getId(){
    return id;
  }
  public String getName(){
    return name;
  } 
  public String getSex(){
    return sex;
  }
  public float getNum(){
    return num;
  }
  public int getAge(){
    return age;
  }
  public BigInteger getLargeNum(){
    return largeNum;
  }

  public void setId(Integer id){
    this.id = id;
  }
  public void setName(String name){
    this.name = name;
  }
  public void setSex(String sex){
    this.sex = sex;
  }
  public void setNum(float num){
    this.num = num;
  }
  public void setAge(int age){
    this.age = age;
  }
  public void setLargeNum(BigInteger b){
    this.largeNum = b;
  }

  public Teacher(Integer id,String name, String sex, float num, int age, BigInteger largeNum){
    super();
    this.id = id;
    this.name = name;
    this.sex = sex;
    this.num = num;
    this.age = age;
    this.largeNum = largeNum;
  }

  public Teacher(String name, String sex, float num, int age, BigInteger largeNum){
    super();
    this.name = name;
    this.sex = sex;
    this.num = num;
    this.age = age;
    this.largeNum = largeNum;
  }

  public Teacher(String name, String sex){
    super();
    this.name = name;
    this.sex = sex;
  }

  public Teacher(){
    super();
  }
  
  @Override
  public String toString(){
    return "Person [id="+id+", name=" + name + ", sex=" + sex + ", num=" + num + ", age=" +age+ 
      ", largeNum="+ largeNum +"]";
  }

  private void tableScript(){
    //     CREATE TABLE `teacher` (
    //   `id` int(11) NOT NULL AUTO_INCREMENT,
    //   `name` varchar(50) NOT NULL,
    //   `sex` char(1) DEFAULT 'M',
    //   `num` float(10,3) DEFAULT 0.000,
    //   `age` int(5) DEFAULT 0,
    //   `largeNum` bigint(20) DEFAULT NULL,
    //   PRIMARY KEY (`id`)
    // ) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8
  }
  
}










