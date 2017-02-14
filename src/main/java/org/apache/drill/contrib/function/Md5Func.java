package org.apache.drill.contrib.function;

import com.google.common.hash.HashFunction;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import io.netty.buffer.DrillBuf;
import org.apache.drill.exec.expr.DrillSimpleFunc;
import org.apache.drill.exec.expr.annotations.FunctionTemplate;
import org.apache.drill.exec.expr.annotations.Output;
import org.apache.drill.exec.expr.annotations.Param;
import org.apache.drill.exec.expr.holders.NullableVarCharHolder;
import org.apache.drill.exec.expr.holders.VarCharHolder;

import javax.inject.Inject;


@FunctionTemplate(
        name = "md5",
        scope = FunctionTemplate.FunctionScope.SIMPLE,
        nulls = FunctionTemplate.NullHandling.NULL_IF_NULL
)
public class Md5Func implements DrillSimpleFunc {

    @Param
    NullableVarCharHolder input;

    @Output
    VarCharHolder out;

    @Inject
    DrillBuf buffer;


    public void setup() {
    }


    public void eval() {

        // get the value and replace with
        String stringValue = org.apache.drill.exec.expr.fn.impl.StringFunctionHelpers.toStringFromUTF8(input.start, input.end, input.buffer);

        // build the hash
        com.google.common.hash.HashFunction md = com.google.common.hash.Hashing.md5();
        com.google.common.hash.HashCode code = md.hashBytes(stringValue.getBytes());
        String outputValue = code.toString();

        // put the output value in the out buffer
        out.buffer = buffer;
        out.start = 0;
        out.end = outputValue.getBytes().length;
        buffer.setBytes(0, outputValue.getBytes());

    }


}


