
/**
 * Class to fetch in queries in database
 */
export class Fetch {
    type: string;
    value: string;

    constructor(type: string, value: string) {
        this.type = type;
        this.value = value;
    }
}