module.exports = {
    verbose: true,
    preset: "jest-preset-angular",
    globalSetup: "jest-preset-angular/global-setup",
    collectCoverage: true,
    coverageDirectory: "dist/test-coverage",
    setupFilesAfterEnv: ['<rootDir>/setup-jest.ts'],
    transform: {
        '^.+\\.spec.ts?$': ['jest-preset-angular', {
            babel: true,
            tsconfig: 'tsconfig.json',
        }]
    }
}