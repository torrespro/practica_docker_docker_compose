import { Sequelize } from 'sequelize';
import mysql2 from 'mysql2';
import DebugLib from 'debug';

const dbHost = process.env.MYSQL_HOST || "localhost";
const dbPort = process.env.MYSQL_PORT || "3306";
const database = process.env.MYSQL_DB || "eoloplantsDB";

const debug = new DebugLib('server:mysql');

export default new Sequelize(database, 'root', 'password', {
    host: dbHost,
    dialect: 'mysql',
    dialectModule: mysql2,
    logging: false
});

process.on('exit', async () => {
    await sequelize.close();
    debug(`Closing mysql connection`);
});
