public class TplClass6454 {

    private static final void method(java.lang.String[] args, java.lang.String host, boolean inline, int port) throws Throwable {
        host = args[1];
        port = Integer.parseInt(args[2]);
        if (args.length > 3) {
            inline = args[2].equals("-inline");
        }
    }
}

