let crypto = require("crypto");

export function getMd5(data){
  return crypto.createHash('md5').update(data, 'utf8').digest('hex');
}
