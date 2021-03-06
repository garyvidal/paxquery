/*******************************************************************************
 * Copyright (C) 2013, 2014, 2015 by Inria and Paris-Sud University
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package fr.inria.oak.paxquery.algebra.operators.binary;

import fr.inria.oak.paxquery.algebra.operators.BaseLogicalOperator;
import fr.inria.oak.paxquery.common.datamodel.metadata.NestedMetadataUtils;
import fr.inria.oak.paxquery.common.exception.PAXQueryExecutionException;
import fr.inria.oak.paxquery.common.predicates.BasePredicate;

/**
 * Left outer nested join logical operator.
 *
 */
public class LeftOuterNestedJoin extends BaseJoinOperator {

  private int documentIDColumn;

  private int[] nodeIDColumns;

  public LeftOuterNestedJoin(BaseLogicalOperator left, BaseLogicalOperator right,
          BasePredicate pred, int documentIDColumn, int[] nodeIDColumns)
          throws PAXQueryExecutionException {
    super(left, right, pred);

    this.visible = true;
    this.ownName = "LeftOuterNestedJoin";
    this.documentIDColumn = documentIDColumn;
    this.nodeIDColumns = nodeIDColumns;
  }

  @Override
  public void buildNRSMD() {
    this.nestedMetadata = NestedMetadataUtils.addNestedField(getLeft().getNRSMD(), getRight().getNRSMD());  
  }

  public int getDocumentIDColumn() {
    return this.documentIDColumn;
  }

  public void setDocumentIDColumn(int documentIDColumn) {
    this.documentIDColumn = documentIDColumn;
  }

  public int[] getNodeIDColumns() {
    return this.nodeIDColumns;
  }

  public void setNodeIDColumns(int[] nodeIDColumns) {
    this.nodeIDColumns = nodeIDColumns;
  }
}
