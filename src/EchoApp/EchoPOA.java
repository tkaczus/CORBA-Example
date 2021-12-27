package EchoApp;


/**
 * EchoApp/EchoPOA.java .
 * Generated by the IDL-to-Java compiler (portable), version "3.2"
 * from Echo.idl
 * Friday, June 29, 2018 4:29:11 PM EAT
 */

public abstract class EchoPOA extends org.omg.PortableServer.Servant
        implements EchoApp.EchoOperations, org.omg.CORBA.portable.InvokeHandler {

    // Constructors

    private static java.util.Hashtable _methods = new java.util.Hashtable();

    static {
        _methods.put("PIK_OtworzSesje", new java.lang.Integer(0));
        _methods.put("PIK_WybierzSerwer", new java.lang.Integer(1));
        _methods.put("PIK_SprawdzSerwer", new java.lang.Integer(2));
        _methods.put("PIK_ZeZmiennej", new java.lang.Integer(3));
    }

    public org.omg.CORBA.portable.OutputStream _invoke(String $method,
                                                       org.omg.CORBA.portable.InputStream in,
                                                       org.omg.CORBA.portable.ResponseHandler $rh) {
        org.omg.CORBA.portable.OutputStream out = null;
        java.lang.Integer __method = (java.lang.Integer) _methods.get($method);
        if (__method == null)
            throw new org.omg.CORBA.BAD_OPERATION(0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

        switch (__method.intValue()) {
            case 0:  // EchoApp/Echo/echoString
            {
                int $result = 0;
                $result = this.PIK_OtworzSesje();
                out = $rh.createReply();
                out.write_long($result);
                break;
            }
            case 1:  // EchoApp/Echo/echoString
            {
                int $result = 0;
                $result = this.PIK_WybierzSerwer();
                out = $rh.createReply();
                out.write_long($result);
                break;
            }
            case 2:  // EchoApp/Echo/echoString
            {
                int $result = 0;
                $result = this.PIK_SprawdzSerwer();
                out = $rh.createReply();
                out.write_long($result);
                break;
            }
            case 3:  // EchoApp/Echo/echoString
            {
                String $result = null;
                $result = this.PIK_ZeZmiennej();
                out = $rh.createReply();
                out.write_string($result);
                break;
            }

            default:
                throw new org.omg.CORBA.BAD_OPERATION(0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
        }

        return out;
    } // _invoke


    // Type-specific CORBA::Object operations
    private static String[] __ids = {
            "IDL:EchoApp/Echo:1.0"};

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] objectId) {
        return (String[]) __ids.clone();
    }

    public Echo _this() {
        return EchoHelper.narrow(
                super._this_object());
    }

    public Echo _this(org.omg.CORBA.ORB orb) {
        return EchoHelper.narrow(
                super._this_object(orb));
    }


} // class EchoPOA
