
package org.sysu.workflow.io;

import org.sysu.workflow.*;
import org.sysu.workflow.model.extend.InheritableContext;
import org.sysu.workflow.env.MultiStateMachineDispatcher;
import org.sysu.workflow.env.SimpleErrorReporter;
import org.sysu.workflow.env.jexl.JexlEvaluator;
import org.sysu.workflow.model.Data;
import org.sysu.workflow.model.Datamodel;
import org.sysu.workflow.model.SCXML;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;
import org.sysu.workflow.model.extend.MessageMode;
import org.sysu.workflow.model.extend.Task;
import org.sysu.workflow.model.extend.Tasks;

import java.io.*;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Unit tests
 */
public class SCXMLReaderTest {

    public static synchronized String encrypt(String text)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(text.getBytes("UTF-8"));
        byte raw[] = md.digest();
        return new Base64(-1).encodeToString(raw);            // -1 means no line breaks
    }


    public static synchronized String encrypt(String text, String defText) {
        if (defText == null) defText = text;
        try {
            return encrypt(text);
        }
        catch (Exception e) {
            return defText;
        }
    }



    @Test
    public void testSCXMLReader() throws Exception {

//        EngineClient ec = new EngineClient();
//        Map<String, String> args = new HashMap<String, String>();
//        args.put("action", "connect");
//        args.put("userid", "admin");
//        args.put("password", "2333333");
        //ec.executeGet("http://localhost:8827/gateway", args);
        //EngineServer.AsyncBeginAccept();


        //while (true){
        //    Thread.sleep(10);
        //}

//        String str = "YAWL";
//        String tt = encrypt(str);

        long startTime=System.currentTimeMillis();
        URL url = SCXMLTestHelper.getResource("GuestOrder.xml");
        //URL url = new URL("file", "", "E:\\Documents\\GitProject\\BOOWorkflow\\BOWorkflow\\target\\classes\\GuestOrder.xml");
        SCXML scxml = new SCXMLReader().read(url);
        long endTime=System.currentTimeMillis();
        System.out.println("COST TIME： " + (endTime-startTime) + "ms");

//        startTime=System.currentTimeMillis();
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);
//        out.writeObject(scxml);
//        String deptString = byteArrayOutputStream.toString("ISO-8859-1");//必须是ISO-8859-1
//        System.out.println("COST TIME： " + (endTime-startTime) + "ms");
//
//        startTime=System.currentTimeMillis();
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(deptString.getBytes("ISO-8859-1"));
//        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
//        SCXML scxml2 = (SCXML) objectInputStream.readObject();
//        endTime=System.currentTimeMillis();
//        System.out.println("COST TIME： " + (endTime-startTime) + "ms");

        scxml.getTasks().getTaskList().get(0).getPrinciple().GenerateDescriptor();
        Datamodel dm = scxml.getDatamodel();
        System.out.println("## guest order的data");
        for(Data data:dm.getData()){
            System.out.println(data.getId() + ":" + data.getExpr());
        }

        InheritableContext inheritableContext = scxml.getInheritedContext();
        Tasks tasks = inheritableContext.getInheritedTasks();
        System.out.println("## guest order的可继承上下文的tasks");
        for(Task task:tasks.getTaskList()){
            System.out.println(task.getName()+":"+task.getId());
        }
        System.out.println("## guest order本身的tasks");
        for(Task task:scxml.getTasks().getTaskList()){
            System.out.println(task.getName()+":"+task.getId());
        }

        Evaluator evaluator = new JexlEvaluator();
        EventDispatcher dispatcher = new MultiStateMachineDispatcher();
        SCXMLExecutor executor = new SCXMLExecutor(evaluator, dispatcher, new SimpleErrorReporter());
        executor.setStateMachine(scxml);
        executor.go();



        SCXMLExecutionContext ctx = executor.getExctx();

        dispatcher.send(ctx.RootTid, ctx.Tid, "", MessageMode.MULTICAST, "GuestOrder", "", SCXMLIOProcessor.DEFAULT_EVENT_PROCESSOR,
                "submit", null, "", 0);
        System.out.println("send submit");

        dispatcher.send(ctx.RootTid, ctx.Tid, "", MessageMode.TO_CHILD, "KitchenOrder", "", SCXMLIOProcessor.DEFAULT_EVENT_PROCESSOR,
                "produced", null, "", 0);
        System.out.println("send produced");

        EventDataPackage edp = new EventDataPackage();
        edp.passed = "1";
        dispatcher.send(ctx.RootTid, ctx.Tid, "", MessageMode.TO_CHILD, "KitchenOrder", "", SCXMLIOProcessor.DEFAULT_EVENT_PROCESSOR,
                "testCompleted", edp, "", 0);
        System.out.println("send testCompleted");

        dispatcher.send(ctx.RootTid, ctx.Tid, "", MessageMode.TO_CHILD, "KitchenOrder", "", SCXMLIOProcessor.DEFAULT_EVENT_PROCESSOR,
                "delivered", null, "", 0);
        System.out.println("send delivered");

        dispatcher.send(ctx.RootTid, ctx.Tid, "", MessageMode.TO_CHILD, "KitchenOrder", "", SCXMLIOProcessor.DEFAULT_EVENT_PROCESSOR,
                "archived", null, "", 0);
        System.out.println("send archived");

        dispatcher.send(ctx.RootTid, ctx.Tid, "", MessageMode.MULTICAST, "GuestOrder", "", SCXMLIOProcessor.DEFAULT_EVENT_PROCESSOR,
                "requestCheck", null, "", 0);
        System.out.println("send requestCheck");

        dispatcher.send(ctx.RootTid, ctx.Tid, "", MessageMode.TO_CHILD, "GuestCheck", "", SCXMLIOProcessor.DEFAULT_EVENT_PROCESSOR,
                "calculated", null, "", 0);
        System.out.println("send calculated");

        dispatcher.send(ctx.RootTid, ctx.Tid, "", MessageMode.TO_CHILD, "GuestCheck", "", SCXMLIOProcessor.DEFAULT_EVENT_PROCESSOR,
                "paid", null, "", 0);
        System.out.println("send paid");

        dispatcher.send(ctx.RootTid, ctx.Tid, "", MessageMode.TO_CHILD, "GuestCheck", "", SCXMLIOProcessor.DEFAULT_EVENT_PROCESSOR,
                "archived", null, "", 0);
        System.out.println("send archived");

        Assert.assertNotNull(scxml);
    }

    public class EventDataPackage {
        public String passed;
    }


}

