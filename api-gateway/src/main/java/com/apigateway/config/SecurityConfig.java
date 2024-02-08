package com.apigateway.config;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;




//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@NoArgsConstructor
//@AllArgsConstructor
//public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private  static final String chattopic =topicname;

  //  private static final  String GROUP_ID ="chat";


    //http://localhost:8085/series/test/consumer
//    @Configuration
//    @RequestMapping("consumer/chat")
//    public class kafkaconfig {
//        //kafkafdlistner will listen it will get the messages which are comming to a topic wwe provided
//        private SecurityConfig pass;


//        public SecurityConfig getPass() {
//            return pass;
//        }
//
//        public void setPass(SecurityConfig pass) {
//            this.pass = pass;
//        }
//
//        public static String Passedvalue ;

//        @KafkaListener(topics = "connecttopic",groupId = GROUP_ID)
//        public void updatedlocation(String value){
//            Passedvalue = value;
//            System.out.println(value);
//
//        }
//        @GetMapping
//        public ResponseEntity<?> passvalue (){
//            return new ResponseEntity<>(Passedvalue,HttpStatus.OK);
//        }
//    }


//    @Bean
//    public NewTopic topic(){
//        return TopicBuilder
//                .name()
//                //   .partitions()
//                .build();
   // }
//    @Bean
//    public PasswordEncoder getencoderpassword(){
//        return new BCryptPasswordEncoder();
//    }
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//             //   .antMatchers(HttpMethod.GET,"all").hasRole("ADMIN")
//
//                .anyRequest()
//                .authenticated() // All other requests require authentication
//                .and();
//        http.httpBasic();
//
//    }
//
//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//
//        UserDetails admin = User.builder().username("rahul").password(passwordEncoder.encode("testing")).roles("ADMIN").build();
//        UserDetails user = User.builder().username("sumit").password(passwordEncoder.encode("adtesting")).roles("USER").build();
//
//
//        return new InMemoryUserDetailsManager(user,admin);
//    }
//}
//
