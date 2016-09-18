package org.prueba.bean;

import erp.entity.PersonaEmpresaUsuario;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import org.prueba.jpa.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

@ManagedBean
@RequestScoped
public class JsfBean {
    
    private String data;

    @Autowired
    private UserDao userDao;
    private String welcomeMessage = "Populated by JSF created bean 11111";
    
    @PostConstruct
    public void init() {
        data = "[{\n" +
"                    name: 'Microsoft Internet Explorer',\n" +
"                    y: 56.33\n" +
"                }, {\n" +
"                    name: 'Chrome',\n" +
"                    y: 24.03,\n" +
"                    sliced: true,\n" +
"                    selected: true\n" +
"                }, {\n" +
"                    name: 'Firefox',\n" +
"                    y: 10.38\n" +
"                }, {\n" +
"                    name: 'Safari',\n" +
"                    y: 4.77\n" +
"                }, {\n" +
"                    name: 'Opera',\n" +
"                    y: 0.91\n" +
"                }, {\n" +
"                    name: 'Proprietary or Undetectable',\n" +
"                    y: 0.2\n" +
"                }]";
    }

    public String getWelcomeMessage() {
//            
//        
        List<PersonaEmpresaUsuario> l = userDao.getAll();
        for (PersonaEmpresaUsuario p : l) {
            System.out.println("persona base qw" + p.getCiRuc());
            break;
        }
        return welcomeMessage;
    }

    public String login() {
        return "/pages/index2.xhtml?faces-redirect=true&ppp=hola";
        //return "/pages/index2.xhtml";
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    
    
}
