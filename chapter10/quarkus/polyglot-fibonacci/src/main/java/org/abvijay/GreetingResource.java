package org.abvijay;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

@Path("/hello-resteasy")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        Context ctx = Context.create("js");
        String resultStr = "";
        try {
            File mathJSFile = new File("./math.js");
            ctx.eval(Source.newBuilder("js", mathJSFile).build());

            Value addFunction = ctx.getBindings("js").getMember("add");
            Value subtractFunction = ctx.getBindings("js").getMember("subtract");
            Value multiplyFunction = ctx.getBindings("js").getMember("multiply");
            Value helloMathMessage = ctx.getBindings("js").getMember("helloMathMessage");

            System.out.println("Binding Keys :" + ctx.getBindings("js").getMemberKeys());

            Integer addResult = addFunction.execute(30, 20).asInt();
            Integer subtractResult = subtractFunction.execute(30, 20).asInt();
            Integer multiplyResult = multiplyFunction.execute(30, 20).asInt();

            resultStr = "Add Result "+ addResult+ " Subtract Result "+ subtractResult+ " Multiply Result "+ multiplyResult + " helloMathMessage : " + helloMathMessage.toString();


        }   catch (Exception e) {
            System.out.println("Exception : " );
            e.printStackTrace();
            resultStr = e.getMessage();
        }


        return resultStr;
    }
}