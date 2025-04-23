export class UpdateTrainee {
    constructor(private username: string,
        private firstname: string,
        private lastname: string,
        private dateOfBirth: Date,
        private address: string,
        private active: boolean) { }
}