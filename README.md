# springboot-oauth
Spring Boot集成Spring security OAuth2事例
- oauth：认证服务器、资源服务器
- client-1：资源服务器，与oauth实现SSO
- client-2：资源服务器，使用RemoteTokenServices进行token验证（无法同时使用SSO和OAuth来保护同一资源）
- client-3：调用客户端，放开所有访问权限，通过OAuth2RestTemplate调用client-4（client_credentials模式）
- client-4：资源服务器，自定义ExpressionHandler实现scop匹配，即client的scopes为rea.*，将正常通过hasScopeMatching('read')的鉴权
- client-5：资源服务器，使用JWT公钥验证token
