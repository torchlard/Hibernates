package DB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.jsqlparser.*;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.arithmetic.*;
import net.sf.jsqlparser.expression.operators.conditional.*;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.values.ValuesStatement;

// FromItemVisitor, ExpressionVisitor, ItemsListVisitor,
public class JSQLTools implements SelectVisitor, SelectItemVisitor {

  private NaviSoftRealMeta meta;
  private List tables;
  private List columns;
  private String nameColumn;
  private List<NaviSoftRealMeta> metaDataColumns;
  private Select select;

  public List getTableList(Select select) {
      tables = new ArrayList();
      this.select = select;
      select.getSelectBody().accept(this);
      return tables;
  }

  public List getColumnList(Select select) {
      columns = new ArrayList<>();
      this.select = select;
      select.getSelectBody().accept(this);
      c.close();
      return columns;

  }

  public List<NaviSoftRealMeta> getColumnListTest(Select select) {
      metaDataColumns = new ArrayList<NaviSoftRealMeta>();
      select.getSelectBody().accept(this);
      return metaDataColumns;
  }

  public String getDeleteSQL(Delete delete) {
      return delete.toString();

  }

  @Override
  public void visit(PlainSelect plainSelect) {
      if (plainSelect.getSelectItems() != null) {
          for (Iterator SIt = plainSelect.getSelectItems().iterator(); SIt.hasNext();) {
              SelectItem SI = (SelectItem) SIt.next();
              SI.accept(this);
          }
      }
      plainSelect.getFromItem().accept(this);

      if (plainSelect.getJoins() != null) {
          for (Iterator joinsIt = plainSelect.getJoins().iterator(); joinsIt.hasNext();) {
              Join join = (Join) joinsIt.next();
              join.getRightItem().accept(this);
          }
      }
      if (columns == null || metaDataColumns == null) {
          if (plainSelect.getWhere() != null) {
              plainSelect.getWhere().accept(this);
          }
      }
  }

  // @Override
  // public void visit(Table tableName) {
  //     if (!(tables == null)) {
  //         String tableWholeName = tableName.getName();
  //         tables.add(tableWholeName);
  //     }
  // }

  // @Override
  // public void visit(SubSelect subSelect) {
  //     subSelect.getSelectBody().accept(this);
  // }

  // @Override
  // public void visit(Addition addition) {
  //     visitBinaryExpression(addition);
  // }

  // @Override
  // public void visit(AndExpression andExpression) {
  //     visitBinaryExpression(andExpression);
  // }

  // @Override
  // public void visit(Between between) {
  //     between.getLeftExpression().accept(this);
  //     between.getBetweenExpressionStart().accept(this);
  //     between.getBetweenExpressionEnd().accept(this);
  // }

  // @Override
  // public void visit(Column tableColumn) {
  //     if (!(columns == null)) {
  //         columns.add(tableColumn.getColumnName());
  //     }
  //     if (!(metaDataColumns == null)) {
  //         meta = new NaviSoftRealMeta();
  //         nameColumn = tableColumn.getColumnName();
  //         meta.setTable(tableColumn.getTable().getName());
  //         meta.setFieldName(nameColumn);
  //     }
  // }

  // @Override
  // public void visit(Division division) {
  //     visitBinaryExpression(division);
  // }

  // @Override
  // public void visit(DoubleValue doubleValue) {
  // }

  // @Override
  // public void visit(EqualsTo equalsTo) {
  //     visitBinaryExpression(equalsTo);
  // }

  // @Override
  // public void visit(Function function) {

  // }

  // @Override
  // public void visit(GreaterThan greaterThan) {
  //     visitBinaryExpression(greaterThan);
  // }

  // @Override
  // public void visit(GreaterThanEquals greaterThanEquals) {
  //     visitBinaryExpression(greaterThanEquals);
  // }

  // @Override
  // public void visit(InExpression inExpression) {
  //     inExpression.getLeftExpression().accept(this);
  //     inExpression.getItemsList().accept(this);
  // }

  // @Override
  // public void visit(InverseExpression inverseExpression) {
  //     inverseExpression.getExpression().accept(this);
  // }

  // @Override
  // public void visit(IsNullExpression isNullExpression) {
  // }

  // @Override
  // public void visit(JdbcParameter jdbcParameter) {
  // }

  // @Override
  // public void visit(LikeExpression likeExpression) {
  //     visitBinaryExpression(likeExpression);
  // }

  // @Override
  // public void visit(ExistsExpression existsExpression) {
  //     existsExpression.getRightExpression().accept(this);
  // }

  // @Override
  // public void visit(LongValue longValue) {
  // }

  // @Override
  // public void visit(MinorThan minorThan) {
  //     visitBinaryExpression(minorThan);
  // }

  // @Override
  // public void visit(MinorThanEquals minorThanEquals) {
  //     visitBinaryExpression(minorThanEquals);
  // }

  // @Override
  // public void visit(Multiplication multiplication) {
  //     visitBinaryExpression(multiplication);
  // }

  // @Override
  // public void visit(NotEqualsTo notEqualsTo) {
  //     visitBinaryExpression(notEqualsTo);
  // }

  // @Override
  // public void visit(NullValue nullValue) {
  // }

  // @Override
  // public void visit(OrExpression orExpression) {
  //     visitBinaryExpression(orExpression);
  // }

  // @Override
  // public void visit(Parenthesis parenthesis) {
  //     parenthesis.getExpression().accept(this);
  // }

  // @Override
  // public void visit(StringValue stringValue) {
  // }

  // @Override
  // public void visit(Subtraction subtraction) {
  //     visitBinaryExpression(subtraction);
  // }

  // public void visitBinaryExpression(BinaryExpression binaryExpression) {
  //     binaryExpression.getLeftExpression().accept(this);
  //     binaryExpression.getRightExpression().accept(this);
  // }

  // @Override
  // public void visit(ExpressionList expressionList) {
  //     for (Iterator iter = expressionList.getExpressions().iterator(); iter.hasNext();) {
  //         Expression expression = (Expression) iter.next();
  //         expression.accept(this);
  //     }

  // }

  // @Override
  // public void visit(DateValue dateValue) {
  // }

  // @Override
  // public void visit(TimestampValue timestampValue) {
  // }

  // @Override
  // public void visit(TimeValue timeValue) {
  // }

  // @Override
  // public void visit(CaseExpression caseExpression) {
  // }

  // @Override
  // public void visit(WhenClause whenClause) {
  // }

  // @Override
  // public void visit(AllComparisonExpression allComparisonExpression) {
  //     allComparisonExpression.getSubSelect().getSelectBody().accept(this);
  // }

  // @Override
  // public void visit(AnyComparisonExpression anyComparisonExpression) {
  //     anyComparisonExpression.getSubSelect().getSelectBody().accept(this);
  // }

  // @Override
  // public void visit(SubJoin subjoin) {
  //     // subjoin.getLeft().accept(this);
  //     // subjoin.getJoinList().getRightItem().accept(this);
  // }

  // @Override
  // public void visit(Concat concat) {
  //     throw new UnsupportedOperationException("Not supported yet.");
  // }

  // @Override
  // public void visit(Matches mtchs) {
  //     throw new UnsupportedOperationException("Not supported yet.");
  // }

  // @Override
  // public void visit(BitwiseAnd ba) {
  //     throw new UnsupportedOperationException("Not supported yet.");
  // }

  // @Override
  // public void visit(BitwiseOr bo) {
  //     throw new UnsupportedOperationException("Not supported yet.");
  // }

  // @Override
  // public void visit(BitwiseXor bx) {
  //     throw new UnsupportedOperationException("Not supported yet.");
  // }

  @Override
  public void visit(AllColumns ac) {
      // if (!(columns == null)) {
      //     List<NaviSoftFieldConfig> configfield;
      //     List<NaviSoftField> configColumnList = new ArrayList<>();
      //     Integer Index = 0;
      //     Boolean Exists = false;
      //     try {
      //         configfield = NaviSoftToolsServer.getMetaDataTemp(c, select.getSelectBody().toString());
      //         for (int i = 0; i < configfield.size(); i++) {
      //             if (configColumnList.isEmpty()) {
      //                 NaviSoftField NSF = new NaviSoftField();
      //                 NSF.setFieldName(configfield.get(i).getFieldName());
      //                 NSF.setIndex(Index);
      //                 configColumnList.add(NSF);
      //                 columns.add(NSF.getFieldName());
      //                 Exists = true;
      //             } else {
      //                 Exists = false;
      //                 Index = 0;
      //                 for (int j = 0; j < configColumnList.size(); j++) {
      //                     if (configColumnList.get(j).getFieldName().equalsIgnoreCase(configfield.get(i).getFieldName())) {
      //                         Index = configColumnList.get(j).getIndex();
      //                         configColumnList.get(j).setIndex(Index + 1);
      //                         Exists = true;
      //                         columns.add(configColumnList.get(j).getFieldName() + configColumnList.get(j).getIndex());

      //                     }
      //                 }
      //             }
      //             if (!Exists) {
      //                 NaviSoftField NSF = new NaviSoftField();
      //                 NSF.setFieldName(configfield.get(i).getFieldName());
      //                 NSF.setIndex(Index);
      //                 configColumnList.add(NSF);
      //                 columns.add(NSF.getFieldName());
      //             }
      //         }
      //     } catch (SQLException ex) {
      //     } catch (JSQLParserException ex) {
      //         // Logger.getLogger(JSQLTools.class.getName()).log(Level.SEVERE, null, ex);
      //         ex.printStackTrace();
      //     }
      // }
  }

  @Override
  public void visit(AllTableColumns atc) {
      atc.getTable().accept(this);


  }

  @Override
  public void visit(SelectExpressionItem sei) {
      sei.getExpression().accept(this);
      if (!(metaDataColumns == null)) {
          if (sei.getAlias() == null) {
              meta.setAliasFieldName(nameColumn);
          } else {
              meta.setAliasFieldName(sei.getAlias().toString());
          }
          metaDataColumns.add(meta);
      }
  }

  @Override
  public void visit(SetOperationList sol) {
      throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visit(WithItem wi) {
      throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visit(ValuesStatement vs){

  }

  // @Override
  // public void visit(LateralSubSelect lss) {
  //     throw new UnsupportedOperationException("Not supported yet.");
  // }

  // @Override
  // public void visit(ValuesList vl) {
  //     throw new UnsupportedOperationException("Not supported yet.");
  // }

  // @Override
  // public void visit(CastExpression ce) {
  //     throw new UnsupportedOperationException("Not supported yet.");
  // }

  // @Override
  // public void visit(Modulo modulo) {
  //     throw new UnsupportedOperationException("Not supported yet.");
  // }

  // @Override
  // public void visit(AnalyticExpression ae) {
  //     throw new UnsupportedOperationException("Not supported yet.");
  // }

  // @Override
  // public void visit(ExtractExpression ee) {
  //     throw new UnsupportedOperationException("Not supported yet.");
  // }

  // @Override
  // public void visit(MultiExpressionList mel) {
  //     throw new UnsupportedOperationException("Not supported yet.");
  // }
}