export const host_address="http://13.234.1.201";
export const environment = {
  production: true,
  manaul_answer:`${host_address}:8091/api/v1/`,
  savedData:`${host_address}:8081/api/v1/saveUser`.toString(),
  loginData:`${host_address}:8081/api/v1/login`.toString(),
  allUserData:`${host_address}:8081/api/v1/users`.toString(),
  singleUserData:`${host_address}:8081/api/v1/user`.toString(),
  chatHistoryService :`${host_address}:9000/api/v1`.toString(),
  botSocket:`${host_address}:8090/socket`.toString(),
  botService:`${host_address}/api/v1`.toString(),
  dialogflow:{
    angularBot:'aa0e2a8f0792403c8119d2410e7aafd1'
  },
};

