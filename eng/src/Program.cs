// Copyright (c) SharpCrafters s.r.o. See the LICENSE.md file in the root directory of this repository root for details.

using PostSharp.Engineering.BuildTools;
using PostSharp.Engineering.BuildTools.Build;
using PostSharp.Engineering.BuildTools.Build.Model;
using PostSharp.Engineering.BuildTools.Build.Solutions;
using PostSharp.Engineering.BuildTools.Dependencies.Definitions;
using Spectre.Console.Cli;
using MetalamaDependencies = PostSharp.Engineering.BuildTools.Dependencies.Definitions.MetalamaDependencies.V2023_4;

var product = new Product( MetalamaDependencies.MetalamaFrameworkRunTime )
{
    Solutions = new Solution[] { new DotNetSolution( "Metalama.Framework.RunTime.sln" ) },
    PublicArtifacts = Pattern.Create( "Metalama.Framework.RunTime.$(PackageVersion).nupkg" ),
    Dependencies = new[] { DevelopmentDependencies.PostSharpEngineering },

    // This is the first version of this repo.
    Configurations =
        Product.DefaultConfigurations.WithValue(
            BuildConfiguration.Public,
            c => c with { RequiresUpstreamCheck = false } )
};

var commandApp = new CommandApp();

commandApp.AddProductCommands( product );

return commandApp.Run( args );