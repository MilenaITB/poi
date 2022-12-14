/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.RefListEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.ptg.NumberPtg;

/**
 * Returns the number of areas in a reference. An area is a range of contiguous cells or a single cell.
 */
public final class Areas implements Function {

    @Override
    public ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        if (args.length == 0) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            ValueEval valueEval = args[0];
            int result = 1;
            if (valueEval instanceof RefListEval) {
                RefListEval refListEval = (RefListEval) valueEval;
                result = refListEval.getList().size();
            }
            return new NumberEval(new NumberPtg(result));
        } catch (Exception e) {
            return ErrorEval.VALUE_INVALID;
        }

    }
}
