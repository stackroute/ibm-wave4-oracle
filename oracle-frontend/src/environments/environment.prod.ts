export const host_address="http://13.234.1.201";
export const environment = {
  production: true,
  saveData:`${host_address}:8081/api/v1/saveUser`,
  loginData:`${host_address}:8081/api/v1/login`,
  allUserData:`${host_address}:8081/api/v1/users`,
  singleUserData:`${host_address}:8081/api/v1/user`,
  chatHistoryService :`${host_address}:9000/api/v1`,
  botSocket:`${host_address}:8090/socket`,
  botService:`${host_address}/api/v1`,
  dialogflow:{
    angularBot:'aa0e2a8f0792403c8119d2410e7aafd1'
  },
};

